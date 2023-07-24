package com.coding.cho.service;

import com.coding.cho.domain.dto.member.MemberSaveDTO;

public interface MemberService {

	void saveProcess(MemberSaveDTO encodePassword);

	boolean emailCheckProcess(String email);

}
