package com.coding.cho.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface GoodsService {

	Map<String, String> uploadSummernoteImgProcess(MultipartFile file);

}
