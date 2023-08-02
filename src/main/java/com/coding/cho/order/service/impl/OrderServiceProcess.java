package com.coding.cho.order.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.coding.cho.common.domain.entity.MemberEntity;
import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.GoodsEntityRepository;
import com.coding.cho.order.CartEntity;
import com.coding.cho.order.CartEntityRepository;
import com.coding.cho.order.CartItemEntity;
import com.coding.cho.order.CartItemEntityREpository;
import com.coding.cho.order.dto.SaveCateDTO;
import com.coding.cho.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceProcess implements OrderService {
	
	private final CartItemEntityREpository cir;
	private final CartEntityRepository cr;
	private final GoodsEntityRepository gr;
	private final MemberEntityRepository mr;
	
	
	@Transactional
	@Override
	public void addGoods(SaveCateDTO dto) {
		
	   MemberEntity me = mr.findByEmail(dto.getEmail()).get();//세션의 이메일 정보로 유저 no값 가져오기
	   Optional<CartEntity> cart2 = cr.findByMemberEntityNo(me.getNo());//유저 no값으로 연결된 카트엔티티가 있는지 없는지 확인하기
	   CartEntity cart;
	   if(cart2.isEmpty()) {//없으면 만들어서 저장
		   cart = CartEntity.createCart(me);
	        cr.save(cart);
	   }else {
		   cart=cart2.get();
	   }
	   
		
		  //자 이제 여기부턴 유저와 1:1 매칭된 카트엔티티 있다잉 
	   GoodsEntity goods = gr.findById(dto.getGno()).get();//받아온 상품 no 로 상품 객체 goods 생성 
	   CartItemEntity cartItem = cir.findByCartEntityNoAndGoodsNo(cart.getNo(),goods.getNo()); // 카트 넘버와 상품 넘버로 카트아이템 엔티티 찾아냄
		  
		  // 상품이 장바구니에 존재하지 않는다면 카트상품 생성 후 추가 
	   if (cartItem == null) { cartItem =
		  CartItemEntity.createCartItem(cart, goods, 1); cir.save(cartItem); 
		  }
		  
		  // 상품이 장바구니에 이미 존재한다면 수량만 증가 
	   else { CartItemEntity update = cartItem;
		  update.setCartEntity(cartItem.getCartEntity());
		  update.setGoods(cartItem.getGoods()); update.addCount(1);
		  update.setCount(update.getCount()); cir.save(update); }
		  
		  // 카트 상품 총 개수 증가 
	   cart.setCount(cart.getCount()+1);
		 
		
		
		
	}

	@Transactional
	@Override
	public void showCart(SaveCateDTO dto,ModelAndView mv) {
		MemberEntity me = mr.findByEmail(dto.getEmail()).get();//유저 이메일로 유저 정보 찾고
		CartEntity userCart = cr.findByMemberEntityNo(me.getNo()).get();//유저 정보로 유저장바구니 가져옴
		List<CartItemEntity> cartItemList =	cir.findByCartEntityNo(userCart.getNo());//유저 장바구니 no로 장바구니 아이템 객체 전부 가져옴
		int totalPrice = 0;
		for (CartItemEntity cartitem : cartItemList) {
            totalPrice += cartitem.getCount() * cartitem.getGoods().getPrice();
        }
		mv.addObject("totalPrice",totalPrice);  
		mv.addObject("totalCount",userCart.getCount());  
		mv.addObject("cartItems",cartItemList);  
		mv.addObject("user",me); 
		

      
		
	}
	@Transactional
	@Override
	public void deleteCount(SaveCateDTO dto) {
		
		 MemberEntity me = mr.findByEmail(dto.getEmail()).get();//세션의 이메일 정보로 유저 no값 가져오기
		 Optional<CartEntity> cart2 = cr.findByMemberEntityNo(me.getNo());//유저 no값으로 연결된 카트엔티티가 있는지 없는지 확인하기
		   CartEntity cart;
		   if(cart2.isEmpty()) {//없으면 만들어서 저장
			   cart = CartEntity.createCart(me);
		        cr.save(cart);
		   }else {
			   cart=cart2.get();
		   }
		   
		   long cno = cart.getNo();
		   CartItemEntity cartItem = cir.findByCartEntityNoAndGoodsNo(cno,dto.getGno());
		   if (cartItem.getCount() > 1) {//카운트가 1보다 크면 1줄인다. 
			   CartItemEntity update = cartItem;
				  update.setCartEntity(cartItem.getCartEntity());
				  update.setGoods(cartItem.getGoods());
				  update.deleteCount(1);
				  update.setCount(update.getCount()); 
				  cir.save(update); 
			}else {
				cir.delete(cartItem);
			}
				  
				  
			   cart.setCount(cart.getCount()-1);
		   
		   
		 	
	}

	

}
