package com.coding.cho.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.coding.cho.common.domain.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "cart")
public class CartEntity {

	@Id 
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long no;
	
	
	@OneToOne
	@JoinColumn(name="uno")
	private MemberEntity member;
	
	@Builder.Default
	@OneToMany(mappedBy = "cart")
	private List<CartItemEntity> cartItems=new ArrayList<>();
	
	
	
	  
	
}
