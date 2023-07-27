package com.coding.cho.goods;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.coding.cho.goods.dto.GoodsListDTO;
import com.coding.cho.goods.dto.GoodsSaveDTO;

public interface GoodsService {

	Map<String, String> uploadSummernoteImgProcess(MultipartFile file);

	void save(GoodsSaveDTO dto);

	Map<String, String> tempUpload(MultipartFile temp);

	List<GoodsListDTO> list();

	void detailProcess(long no, Model model);

	

}
