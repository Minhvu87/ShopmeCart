package com.shopme.common.entity.section;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shopme.common.entity.Category;
import com.shopme.common.entity.IdBasedEntity;

@Entity
@Table(name = "sections_categories")
public class CategorySection extends IdBasedEntity{

	@Column(name = "category_order")
	private int categoryOrder;
	
	@ManyToOne
	@JoinColumn(name = "category_id")	
	private Category category;

	public int getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(int categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
