package com.coding.cho.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.coding.cho.service.GoodsService;
import com.coding.cho.utils.FileUploadUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GoodsServicePorcess implements GoodsService {

	private final AmazonS3Client  client;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.s3.upload-path}")
	private String path;
	
	@Override
	public Map<String, String> uploadSummernoteImgProcess(MultipartFile file) {
		return FileUploadUtil.s3Upload(client, bucketName, path, file);
	}

}
