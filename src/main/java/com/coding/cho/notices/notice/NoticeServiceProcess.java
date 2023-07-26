package com.coding.cho.notices.notice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceProcess implements NoticeService{
	
	private final NoticeRepository nr;
	
	@Override
	public void saveNotice(NoticeDTO dto) {
		nr.save(NoticeEntity.builder()
				.title(dto.getTitle())
				.content(dto.getContent())				
				.build());		
		
	}

	

	@Override
	public void findAll(Model model) {
		List<NoticeListDTO> result = nr.findAll().stream()
									.map(noticeEntity -> new NoticeListDTO(noticeEntity)).collect(Collectors.toList());
		model.addAttribute("list", result);
		
	}

	@Override
	public void detail(long no, Model model) {
		
		model.addAttribute("detail", nr.findById(no).orElseThrow());
		
	}

	@Override
	public void delete(long no) {
		nr.deleteById(no);
		
	}



	

	

}
