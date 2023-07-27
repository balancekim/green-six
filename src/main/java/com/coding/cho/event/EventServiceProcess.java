package com.coding.cho.event;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.coding.cho.common.utils.FileUploadUtil;
import com.coding.cho.goods.GoodsImageEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventServiceProcess implements EventService{
	
	private final AmazonS3Client client;
	private final EventEntityRepository er;
	private final EventImageRepository ir;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.s3.upload-path2}")
	private String uploadPath;
	@Value("${cloud.aws.s3.temp-path2}")
	private String tempPath;
	
	
	@Override
	public void save(EventSaveDTO dto) {
		EventEntity ee = er.save(EventEntity.builder().name(dto.getName()).content(dto.getContent()).build());
		int leg = dto.getBucketKey().length;
		for (int i = 0; i < leg; i++) {
			if (dto.getBucketKey()[i] == "")
				continue;
			ir.save(EventImageEntity.builder().orgName(dto.getOrgName()[i]).newName(dto.getNewName()[i])
					.url(dto.getUrl()[i]).bucketKey(dto.getBucketKey()[i]).isDef(dto.getDef()[i]).event(ee).build());
		}
	}


	@Override
	public Map<String, String> tempUpload(MultipartFile temp) {
		
		return FileUploadUtil.s3Upload(client, bucketName, tempPath, temp);
	}

}
