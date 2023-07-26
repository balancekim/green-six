package com.coding.cho.common.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.coding.cho.goods.dto.GoodsSaveDTO;

public interface GoodsService {

	Map<String, String> uploadSummernoteImgProcess(MultipartFile file);

	void save(GoodsSaveDTO dto);

	Map<String, String> tempUpload(MultipartFile temp);

}
