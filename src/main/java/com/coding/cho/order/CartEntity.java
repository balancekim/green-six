package com.coding.cho.order;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.coding.cho.common.domain.entity.MemberEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name = "cart")
public class CartEntity {

	@Id 
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long no;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="uno")
	private MemberEntity memberEntity;
	
}
