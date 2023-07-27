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
import com.coding.cho.common.utils.FileUploadUtil;
import com.coding.cho.goods.dto.GoodsListDTO;
import com.coding.cho.goods.dto.GoodsSaveDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GoodsServicePorcess implements GoodsService {

	private final AmazonS3Client  client;
	private final GoodsEntityRepository gr;
	private final GoodsImageEntityRepository ir;
	
	
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.s3.upload-path}")
	private String path;
	
	@Override
	public Map<String, String> uploadSummernoteImgProcess(MultipartFile file) {
		return FileUploadUtil.s3Upload(client, bucketName, path, file);
	}

	@Override
	public void save(GoodsSaveDTO dto) {
		
		//1. 상품정보 저장
		GoodsEntity ge  = gr.save(GoodsEntity.builder()
				.name(dto.getName())
				.price(dto.getPrice())
				.build());
		int leg =dto.getBucketKey().length;
		for(int i=0 ; i <leg ; i++) {
			if(dto.getBucketKey()[i]=="") continue;
				ir.save(GoodsImageEntity.builder()
						.orgName(dto.getOrgName()[i])
						.newName(dto.getNewName()[i])
						.url(dto.getUrl()[i])
						.bucketKey(dto.getBucketKey()[i])
						.isDef(dto.getDef()[i])
						.goods(ge)
						.build());
		}
	}
		

	@Override
	public Map<String, String> tempUpload(MultipartFile temp) {
		return FileUploadUtil.s3Upload(client, bucketName, path, temp);
	}
	
	
	@Override
	@Transactional
	public List<GoodsListDTO> list() {
		 return gr.findAll().stream()
				 .map(GoodsListDTO::new)//조회된 엔티티를 GoodsListDTO로 mapping //파일 용량이 큰경우 썸네일 기능을 사용하여 용량을 줄일 수 있다.
				 .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void detailProcess(long no, Model model, GoodsSaveDTO dto) {
		
		 model.addAttribute("detail", gr.findById(no).stream().map(ee->new GoodsSaveDTO(ee)).findFirst().orElseThrow());
	}

	
}
