package com.coding.cho.common.service;

import org.springframework.ui.Model;

public interface CscenterService {


	void faqListProcess(int divNo, int page, Model model);

	void faqBoardListProcess(int page, Model model);

}
