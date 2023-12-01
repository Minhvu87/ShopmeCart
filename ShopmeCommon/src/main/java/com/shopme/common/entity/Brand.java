package com.shopme.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.shopme.common.Constants;

@Entity
@Table(name= "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
    @Column(nullable = false, length = 45, unique =true)
	private String name;
    
    @Column(nullable = false, length = 128)
	private String logo;
    
    @ManyToMany
    @JoinTable(
    		name = "brands_categories",
    		joinColumns = @JoinColumn(name = "brand_id"),
    		inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	public Brand() {
		
	}
	
	public Brand(String name, String logo) {
		this.name = name;
		this.logo = logo;
	}
	

	public Brand(Integer id, String name) {

		this.id = id;
		this.name = name;
	}

	public Brand(String name) {
		// TODO Auto-generated constructor stub
	    this.name = name;
	}

	public Brand(Integer id) {
		// TODO Auto-generated constructor stub
	    this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Brand [id=" + id + ", name=" + name + ", categories=" + categories + "]";
	
	}
	@Transient
	public String getLogoPath() {
		if (this.id == null) return "/images/image-thumbnail.png";
		
		return Constants.S3_BASE_URI +"/brand-logos/" + this.id + "/" + this.logo;		
	}
    
    

}
