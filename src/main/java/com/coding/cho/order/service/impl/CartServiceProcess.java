package com.coding.cho.order.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.GoodsEntityRepository;
import com.coding.cho.order.CartEntity;
import com.coding.cho.order.CartEntityRepository;
import com.coding.cho.order.CartItemEntity;
import com.coding.cho.order.CartItemEntityRepository;
import com.coding.cho.order.dto.CartDTO;
import com.coding.cho.order.service.CartService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CartServiceProcess implements CartService {
	
	private final CartItemEntityRepository ciRepo;
	private final CartEntityRepository cartRepo;
	private final MemberEntityRepository memRepo;
	private final GoodsEntityRepository goodsRepo;
	
	private CartEntity createCart(String email){
		return cartRepo.findByMember_email(email)
				//orElseGet(안쪽내용은 존재하지 않는경우에만 쿼리가 실행함.)
				.orElseGet(()->cartRepo.save(CartEntity.builder()
						.member(memRepo.findByEmail(email).orElseThrow())
						.build()));
	}
	
	@Transactional
	@Override
	public void cartList(String email, Model model) {
		
		model.addAttribute("cart", new CartDTO(createCart(email)));
	}

	//카드에담기
	@Transactional
	@Override
	public void saveProcess(String email, long gno) {
		//카트가 존재하는경우 카드생성
		CartEntity cart=createCart( email);
		GoodsEntity goods=goodsRepo.findById(gno).orElseThrow();
		
		ciRepo.findByCartAndGoods(cart, goods)
			.ifPresentOrElse(
			//이미 저장된 상품이면 count++
			ent->ent.addCount(1), 
			//존재하지 않으면 카드에 저장
			()->ciRepo.save(CartItemEntity.builder()
					.cart(cart)
					.goods(goods)
					.count(1)
					.build()
					));
		
		
		
	}

}
