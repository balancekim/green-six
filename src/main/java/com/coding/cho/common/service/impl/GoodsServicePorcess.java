package com.coding.cho.common.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.coding.cho.common.service.GoodsService;
import com.coding.cho.common.utils.FileUploadUtil;
import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.GoodsEntityRepository;
import com.coding.cho.goods.GoodsImageEntity;
import com.coding.cho.goods.GoodsImageEntityRepository;
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
		gr.save(GoodsEntity.builder()
				.name(dto.getName())
				.price(dto.getPrice())
				.build());
		
		ir.save(GoodsImageEntity.builder()
				.orgName(dto.getOrgName())
				.newName(dto.getNewName())
				.url(dto.getUrl())
				.bucketKey(dto.getBucketKey())
				.build());
	}

	@Override
	public Map<String, String> tempUpload(MultipartFile temp) {
		return FileUploadUtil.s3Upload(client, bucketName, path, temp);
	}

	
}
