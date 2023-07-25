package com.coding.cho;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coding.cho.common.domain.entity.FaqDivision;
import com.coding.cho.common.domain.entity.FaqEntity;
import com.coding.cho.common.domain.entity.FaqEntityRepository;
import com.coding.cho.common.domain.entity.MemberEntity;
import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.common.utils.HtmlEscapeUtils;
import com.coding.cho.franchisee.FranchiseeEntity;
import com.coding.cho.franchisee.FranchiseeRepository;

@SpringBootTest
class GreenSixApplicationTests {
	
	@Autowired
	FaqEntityRepository faq;
	
	@Autowired
	MemberEntityRepository mrp;
	
	@Autowired
	FranchiseeRepository frp;
	
	//@Test
	void 테스트() {
		String title="<a href='#'>안녕하세요--</a>";
		
		String result=HtmlEscapeUtils.escapeHtml(title);
		System.out.println(result);
	}

	//@Test
	void contextLoads() {
		
		//*
		for(FaqDivision div :FaqDivision.values()) {
			
			IntStream.rangeClosed(1, 10).forEach(i->{
				faq.save(FaqEntity.builder()
						.division(div)
						.question(div.getKoDiv()+" 질문 "+i)
						.answer(div.getKoDiv()+" 답 "+i)
						.build());
			});
			
		}
		//*/
	}
	
	//@Test
	void 가맹점() {
		
		frp.save(FranchiseeEntity.builder()
						.name("의정부")
						.status(true)
						.member(mrp.findById(11L).orElseThrow())
						
				
				.build());
		
		
	}

}
