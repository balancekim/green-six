package com.coding.cho;

import java.util.stream.IntStream;

import javax.persistence.Column;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.coding.cho.domain.entity.FaqDivision;
import com.coding.cho.domain.entity.FaqEntity;
import com.coding.cho.domain.entity.FaqEntityRepository;
import com.coding.cho.utils.HtmlEscapeUtils;

@SpringBootTest
class GreenSixApplicationTests {
	
	@Autowired
	FaqEntityRepository faq;
	
	@Test
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

}
