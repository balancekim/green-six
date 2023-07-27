package com.coding.cho.notices.notice;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

public interface NoticeService {

	void saveNotice(NoticeDTO dto);


	void detail(long no, Model model);



	void delete(long no);

	





	void updateProcess(long no, NoticeUpdateDTO dto);







	void findAll(Model model);


	Page<NoticeEntity> noticeList(Pageable pageable);


	List<NoticeEntity> noticeAllList();


	

	

}
