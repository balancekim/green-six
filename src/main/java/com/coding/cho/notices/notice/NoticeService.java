package com.coding.cho.notices.notice;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface NoticeService {

	void saveNotice(NoticeDTO dto);



	ModelAndView noticeList();



	void findAll(Model model);

	

}
