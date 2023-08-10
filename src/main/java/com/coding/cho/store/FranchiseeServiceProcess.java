package com.coding.cho.store;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.coding.cho.common.domain.entity.MemberEntity;
import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.map.StoreEntity;
import com.coding.cho.map.StoreEntityRepository;
import com.coding.cho.order.OrderEntity;
import com.coding.cho.order.OrderEntityRepository;
import com.coding.cho.order.OrderStatus;
import com.coding.cho.order.dto.OrderDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FranchiseeServiceProcess implements FranchiseeService{
	
	private final MemberEntityRepository mr;
	private final StoreEntityRepository sr;
	private final OrderEntityRepository or;
	
	@Override
	public void waiting(String id,Model model) {
		MemberEntity entity=mr.findByEmail(id).orElseThrow();
		StoreEntity store=sr.findByMember(entity).orElseThrow();
		model.addAttribute("store",store);
		
		
	}

	@Transactional
	@Override
	public void updateStatus(long storeNo, boolean status) {
		
		sr.findById(storeNo).map(store->store.changeStatus(status)).orElseThrow();
		
	}
	
	@Transactional
	@Override
	public List<OrderDTO> waiting(String id) {
		MemberEntity entity=mr.findByEmail(id).orElseThrow();
		List<OrderEntity>  orderEntity= or.findByStoreAndOrderStatus(entity.getStore(),OrderStatus.WAIT);
		List<OrderDTO> list=orderEntity.stream().map(ff->new OrderDTO().order(ff)).collect(Collectors.toList());
		return list;
	}
	@Transactional
	@Override
	public List<OrderDTO> processing(String id) {
		MemberEntity entity=mr.findByEmail(id).orElseThrow();
		List<OrderEntity>  orderEntity= or.findByStoreAndOrderStatus(entity.getStore(),OrderStatus.READY);
		List<OrderDTO> list=orderEntity.stream().map(ff->new OrderDTO().order(ff)).collect(Collectors.toList());
		return list;
	}
	
	@Transactional
	@Override
	public void update(long cno) {
		or.findById(cno).map(ent->ent.setStatus(OrderStatus.READY)).orElseThrow();
		//System.out.println(cno);
	}
	@Transactional
	@Override
	public void updateCancel(long cno) {
		or.findById(cno).map(ent->ent.setStatus(OrderStatus.CANCEL)).orElseThrow();
		
	}
	@Transactional
	@Override
	public List<OrderDTO> cancel(String id) {
		MemberEntity entity=mr.findByEmail(id).orElseThrow();
		List<OrderEntity>  orderEntity= or.findByStoreAndOrderStatus(entity.getStore(),OrderStatus.CANCEL);
		List<OrderDTO> list=orderEntity.stream().map(ff->new OrderDTO().order(ff)).collect(Collectors.toList());
		return list;
	}
	@Transactional
	@Override
	public void updateEnd(long cno) {
		or.findById(cno).map(ent->ent.setStatus(OrderStatus.END)).orElseThrow();
		
	}
	@Transactional
	@Override
	public List<OrderDTO> end(String id) {
		MemberEntity entity=mr.findByEmail(id).orElseThrow();
		List<OrderEntity>  orderEntity= or.findByStoreAndOrderStatus(entity.getStore(),OrderStatus.END);
		List<OrderDTO> list=orderEntity.stream().map(ff->new OrderDTO().order(ff)).collect(Collectors.toList());
		return list;
	}
}
