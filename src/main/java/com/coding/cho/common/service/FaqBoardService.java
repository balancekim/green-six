package com.coding.cho.common.service;

import org.springframework.ui.Model;

import com.coding.cho.common.domain.dto.FaqBoardSaveDTO;
import com.coding.cho.common.domain.dto.cscenter.FaqBoardUpdateDTO;

public interface FaqBoardService {

	void save(String userId, FaqBoardSaveDTO dto);


	void faqBoardDetail(long no, Model model);


	void faqBoardModify(long no, Model model);


	void faqBoardDelete(long no);


	boolean isOwner(String name, long no);


	void faqBoardupdate(FaqBoardUpdateDTO dto, long no);

}
