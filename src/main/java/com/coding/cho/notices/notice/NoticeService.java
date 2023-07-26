package com.coding.cho.notices.notice;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface NoticeService {

	void saveNotice(NoticeDTO dto);



	



	void findAll(Model model);



	void detail(long no, Model model);



	void delete(long no);

	

}
