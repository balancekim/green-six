package com.coding.cho.goods;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
import com.coding.cho.goods.dto.SaleListDTO;
import com.coding.cho.goods.dto.SaleSaveDTO;
import com.coding.cho.goods.dto.GoodsDetailDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GoodsServicePorcess implements GoodsService {

	private final AmazonS3Client client;
	private final GoodsEntityRepository gr;
	private final CategoryEntityRepository cr;
	private final GoodsImageEntityRepository ir;
	private final SaleEntityRepository salerepo;
	
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
	public void save(GoodsSaveDTO dto,SaleSaveDTO saledto) {

		// 1. 상품정보 저장
		GoodsEntity ge = gr
				.save(GoodsEntity.builder().name(dto.getName()).price(dto.getPrice()).content(dto.getContent())
						.hotItem(dto.isHotItem()).onSale(dto.isOnSale()).category(cr.findById(dto.getCategory()).orElseThrow()).build());
		int leg = dto.getBucketKey().length;
		for (int i = 0; i < leg; i++) {
			if (dto.getBucketKey()[i] == "")
				continue;
			String newUrl=FileUploadUtil.s3TempToSrc(client, bucketName, path+dto.getNewName()[i], path2+dto.getNewName()[i]);
			ir.save(GoodsImageEntity.builder().orgName(dto.getOrgName()[i]).newName(dto.getNewName()[i])
					.url(newUrl).bucketKey(path2+dto.getNewName()[i]).isDef(dto.getDef()[i]).goods(ge).build());
		}
		FileUploadUtil.clearTemp(client, bucketName, path);
		
		
		
		salerepo.save(SaleEntity.builder()
				.discount(saledto.getDiscount())
				.startDate(saledto.getStartDate().equals("")?null:saledto.getStartDate())
				.endDate(saledto.getEndDate().equals("")?null:saledto.getStartDate())
				.gno(ge)
				.build());
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
	
	
	@Transactional
	@Override
	public void hotItemList(Model model) {
		model.addAttribute("list", gr.findByHotItem(true).stream()
										.limit(4)
										.map(GoodsListDTO::new)
										.collect(Collectors.toList()));
		
	}

	@Override
	@Transactional
	public void detailProcess(long no, Model model) {
		GoodsDetailDTO dto = gr.findById(no).stream().map(ee -> new GoodsDetailDTO(ee)).findFirst().orElseThrow();
		model.addAttribute("detail", dto);
	}

	@Transactional
	@Override
	public void updateProcess(long no, GoodsUpdateDTO dto,SaleSaveDTO savedto) {

		GoodsEntity entity = gr.findById(no).orElseThrow();
		CategoryEntity category = cr.findById(dto.getCategory()).orElseThrow();
		List<GoodsImageEntity> list=entity.getGie();
		
		//이미지 수정안했을때 이미지 작업 X 처리
		//이미지 수정안했을때 - form 안에 BucketKey length 0
		//이미지 수정하면 length 1
		
			for(int i=0; i<list.size();i++) {
				if (dto.getBucketKey() == "")continue;
				String newUrl=FileUploadUtil.s3TempToSrc(client, bucketName, path+dto.getNewName()[i], path2+dto.getNewName()[i]);
				FileUploadUtil.delete(client, bucketName, list.get(i).getBucketKey());
				ir.save(list.get(i).update(dto, i ,newUrl,path2));
			}
		
		entity.update(dto, category);
		FileUploadUtil.clearTemp(client, bucketName, path);
		
		SaleEntity sale = salerepo.findByGnoNo(no).orElseThrow();
		sale.updateSale(savedto);
		
	}

	@Transactional
	@Override
	public void deleteProcess(long no) {
		GoodsEntity entity=gr.findById(no).orElseThrow();
		String bucketKey=entity.getGie().get(0).getBucketKey();
		FileUploadUtil.delete(client, bucketName, bucketKey);
		entity.getGie().forEach(en->ir.delete(en));
		//SaleEntity saEntity= salerepo.findByGnoNo(no).orElseThrow();
		salerepo.deleteByGnoNo(no);
		gr.deleteById(no);
	}

	@Transactional
	@Override
	public void saleList(Model model) {
		
		LocalDate today= LocalDate.now();
		DateTimeFormatter fomatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedToday=today.format(fomatter);
		/*
		 * Sort sort=Sort.by(Direction.ASC, "startDate"); Pageable
		 * pageable=PageRequest.of(0, 4, sort);
		 */
		
		//*
		List<SaleEntity> result= salerepo.
				findByStartDateLessThanEqualAndEndDateGreaterThanEqualOrStartDateIsNull(formattedToday,formattedToday);
		System.out.println("리스트 : "+result.size());
		
		model.addAttribute("list", result.stream()
				.limit(4)
				.map(SaleListDTO::new)
				.collect(Collectors.toList()));
		//*/
		
		
	}

	
	

}
