package com.coding.cho.goods;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.coding.cho.category.CategoryEntity;
import com.coding.cho.category.CategoryEntityRepository;
import com.coding.cho.common.utils.FileUploadUtil;
import com.coding.cho.goods.dto.GoodsListDTO;
import com.coding.cho.goods.dto.GoodsSaveDTO;
import com.coding.cho.goods.dto.GoodsUpdateDTO;
import com.coding.cho.goods.dto.GoodsDetailDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GoodsServicePorcess implements GoodsService {

	private final AmazonS3Client client;
	private final GoodsEntityRepository gr;
	private final CategoryEntityRepository cr;
	private final GoodsImageEntityRepository ir;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.s3.temp-path}")
	private String path;
	@Value("${cloud.aws.s3.upload-path}")
	private String path2;

	@Override
	public Map<String, String> uploadSummernoteImgProcess(MultipartFile file) {
		return FileUploadUtil.s3Upload(client, bucketName, path, file);
	}

	@Override
	public void save(GoodsSaveDTO dto) {

		// 1. 상품정보 저장
		GoodsEntity ge = gr
				.save(GoodsEntity.builder().name(dto.getName()).price(dto.getPrice()).content(dto.getContent())
						.hotItem(dto.isHotItem()).category(cr.findById(dto.getCategory()).orElseThrow()).build());
		int leg = dto.getBucketKey().length;
		for (int i = 0; i < leg; i++) {
			if (dto.getBucketKey()[i] == "")
				continue;
			String newUrl=FileUploadUtil.s3TempToSrc(client, bucketName, path+dto.getNewName()[i], path2+dto.getNewName()[i]);
			ir.save(GoodsImageEntity.builder().orgName(dto.getOrgName()[i]).newName(dto.getNewName()[i])
					.url(newUrl).bucketKey(path2+dto.getNewName()[i]).isDef(dto.getDef()[i]).goods(ge).build());
		}
		FileUploadUtil.clearTemp(client, bucketName, path);
	}

	@Override
	public Map<String, String> tempUpload(MultipartFile temp) {
		return FileUploadUtil.s3Upload(client, bucketName, path, temp);
	}

	@Override
	@Transactional
	public List<GoodsListDTO> list() {
		return gr.findAll().stream().map(GoodsListDTO::new)// 조회된 엔티티를 GoodsListDTO로 mapping //파일 용량이 큰경우 썸네일 기능을 사용하여
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void detailProcess(long no, Model model) {
		GoodsDetailDTO dto = gr.findById(no).stream().map(ee -> new GoodsDetailDTO(ee)).findFirst().orElseThrow();
		model.addAttribute("detail", dto);
	}

	@Transactional
	@Override
	public void updateProcess(long no, GoodsUpdateDTO dto) {

		GoodsEntity entity = gr.findById(no).orElseThrow();
		CategoryEntity category = cr.findById(dto.getCategory()).orElseThrow();
		List<GoodsImageEntity> list=entity.getGie();
		for(int i=0; i<list.size();i++) {
			System.out.println("dddddddddd"+list.get(i).getBucketKey());
			System.out.println("ffffffffff"+dto.getBucketKey()[i]);
			if (dto.getBucketKey()[i] == "")continue;
			String newUrl=FileUploadUtil.s3TempToSrc(client, bucketName, path+dto.getNewName()[i], path2+dto.getNewName()[i]);
			FileUploadUtil.delete(client, bucketName, list.get(i).getBucketKey());
			ir.save(list.get(i).update(dto, i ,newUrl,path2));
		}
		entity.update(dto, category);
		FileUploadUtil.clearTemp(client, bucketName, path);
		
	}
	

}
