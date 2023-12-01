package com.shopme.common.entity.section;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shopme.common.entity.IdBasedEntity;
import com.shopme.common.entity.product.Product;

@Entity
@Table(name = "sections_products")
public class ProductSection extends IdBasedEntity{

	@Column(name = "product_order")
	private int productOrder;
	
	@ManyToOne
	@JoinColumn(name = "product_id")	
	private Product product;

	public int getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(int productOrder) {
		this.productOrder = productOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
