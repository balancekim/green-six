package com.coding.cho.store;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.coding.cho.common.domain.entity.MemberEntity;
import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.map.StoreEntity;
import com.coding.cho.map.StoreRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FranchiseeServiceProcess implements FranchiseeService{
	
	private final MemberEntityRepository mr;
	private final StoreRepository sr;
	
	
	@Override
	public void waiting(String id,Model model) {
		MemberEntity entity=mr.findByEmail(id).orElseThrow();
		StoreEntity store=sr.findByMember(entity).orElseThrow();
		model.addAttribute("store",store);
		
		
	}

	@Transactional
	@Override
	public void updateStatus(long storeNo, boolean status) {
		
		sr.findById(storeNo).map(store->store.status(status)).orElseThrow();
		
	}


	

}
