package com.coding.cho.faq;

import java.util.List;

public interface FaqCommentService {

	void commentSave(String name, long no, String commentContent);

	List<FaqCommentEntity> findAllByBoardNo(long bno);

}
