package com.coding.cho.common.service;

import com.coding.cho.common.domain.dto.FaqBoardSaveDTO;

public interface FaqBoardService {

	void save(String userId, FaqBoardSaveDTO dto);

}
