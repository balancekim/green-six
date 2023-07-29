package com.coding.cho.category;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.coding.cho.goods.GoodsEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
@Entity
public class CategoryEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_no", nullable =true)//fk
	private CategoryEntity parent;//상위카테고리
	
	@OneToMany(mappedBy = "parent",  cascade = CascadeType.REMOVE)
	private List<CategoryEntity> children;//하위카테고리목록

	public CategoryEntity update(CategoryUpdateDTO dto) {
		name=dto.getName();
		return this;
	}


	
	
	
}
