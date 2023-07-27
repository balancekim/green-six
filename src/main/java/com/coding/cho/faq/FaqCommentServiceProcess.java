package com.coding.cho.faq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.cho.common.domain.entity.FaqBoardEntity;
import com.coding.cho.common.domain.entity.FaqBoardEntityRepository;
import com.coding.cho.common.domain.entity.MemberEntityRepository;

@Service
public class FaqCommentServiceProcess implements FaqCommentService{

	@Autowired
	private FaqCommentEntityRepository repo;
	@Autowired
	private MemberEntityRepository uRepo;
	@Autowired
	private FaqBoardEntityRepository bRepo;
	
	//댓글 저장
	@Override
	public void commentSave(String name, long no, String commentContent) {
		repo.save(FaqCommentEntity.builder()
				.commentContent(commentContent)
				.creator(uRepo.findByEmail(name).orElseThrow())
				.board(FaqBoardEntity.builder().no(no).build())
				.build());
	}

	//댓글 리스트
	@Override
	public List<FaqCommentEntity> findAllByBoardNo(long bno) {
		return repo.findAllByBoard_noOrderByNoDesc(bno);
	}
	
	

}
