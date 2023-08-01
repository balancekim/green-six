package com.coding.cho.goods;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.coding.cho.goods.dto.GoodsSaveDTO;
import com.coding.cho.goods.dto.SaleSaveDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name="Sale")
@Entity
public class SaleEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	private int discount;
	
	private String startDate;
	private String endDate;
	
	@ManyToOne	
	@JoinColumn(name="gno_no")
	private GoodsEntity gno;

	public SaleEntity updateSale(SaleSaveDTO savedto) {
		this.discount=savedto.getDiscount();
		this.startDate=savedto.getStartDate();
		this.endDate=savedto.getEndDate();
		return this;
	}


	
	
}
