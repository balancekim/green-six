package com.coding.cho;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.coding.cho.common.domain.entity.FaqDivision;
import com.coding.cho.common.domain.entity.FaqEntity;
import com.coding.cho.common.domain.entity.FaqEntityRepository;
import com.coding.cho.common.domain.entity.MemberEntity;
import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.common.domain.entity.MyRole;
import com.coding.cho.common.utils.HtmlEscapeUtils;
import com.coding.cho.franchisee.FranchiseeEntity;
import com.coding.cho.franchisee.FranchiseeRepository;
import com.coding.cho.map.StoreEntity;
import com.coding.cho.map.storeRepository;

@SpringBootTest
class GreenSixApplicationTests {
	
	@Autowired
	FaqEntityRepository faq;
	
	@Autowired
	MemberEntityRepository mrp;
	
	@Autowired
	FranchiseeRepository frp;
	
	@Autowired
	storeRepository sr;
	
	//@Test
	void 가맹점넣기() {
		sr.save(StoreEntity.builder()
				.name("강남")
				.engName("gangnam")
				.callNumber("02-3481-1005")
				.faxNumber("02-3481-9122")
				.address("서울 강남구 테헤란로5길 24, 장연빌딩/ 3~7층")
				.build());
	}
	
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
	@Autowired
	PasswordEncoder encoder;
	
	//@Test
	void 어드민계정() {
		mrp.save(MemberEntity.builder().email("user").pass(encoder.encode("1234")).name("유저").build().addRole(MyRole.USER));
	}

}
