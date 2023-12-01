package com.shopme.admin.section;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.common.entity.product.Product;
import com.shopme.common.entity.section.ProductSection;
import com.shopme.common.entity.section.Section;
import com.shopme.common.entity.section.SectionType;
import com.shopme.common.exception.SectionNotFoundException;

@Controller
public class ProductSectionController {

	@Autowired
	private SectionService service;
	
	@GetMapping("/sections/new/product")
	public String showForm(Model model) {
		Section section = new Section(true, SectionType.PRODUCT);
		
		model.addAttribute("section", section);
		model.addAttribute("pageTitle", "Add Product Section");
		
		return "sections/product_section_form";
	}
	
	
	@PostMapping("/sections/save/product")
	public String saveSection(Section section, HttpServletRequest request, RedirectAttributes ra) {
		addProductsToSection(section, request);
		service.saveSection(section);
		ra.addFlashAttribute("message", "The section of type Product has been saved successfully.");
		return "redirect:/sections";
	}
	
	private void addProductsToSection(Section section, HttpServletRequest request) {
		String[] productIds = request.getParameterValues("productId");
		String[] productSectionIds = request.getParameterValues("productSectionId");
		
		if (productIds != null && productIds.length > 0) {
			for (int i = 0; i < productIds.length; i++) {
				ProductSection productSection = new ProductSection();
				
				if (productSectionIds != null && productSectionIds.length > 0) {
					if (i < productSectionIds.length) {
						Integer productSectionId = Integer.valueOf(productSectionIds[i]);
						productSection.setId(productSectionId);
					}
				}
					
				productSection.setProductOrder(i);
				Integer productId = Integer.valueOf(productIds[i]);
				productSection.setProduct(new Product(productId));
				
				section.addProductSection(productSection);
			}
		}
	}
	
	@GetMapping("/sections/edit/Product/{id}")
	public String editSection(@PathVariable(name = "id") Integer id, RedirectAttributes ra,
			Model model) {
		try {
			Section section = service.getSection(id);
			
			model.addAttribute("section", section);
			model.addAttribute("pageTitle", "Edit Product Section (ID: " + id + ")");
			
			return "sections/product_section_form";
			
		} catch (SectionNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			return "redirect:/sections";		
		}
		
	}
}
