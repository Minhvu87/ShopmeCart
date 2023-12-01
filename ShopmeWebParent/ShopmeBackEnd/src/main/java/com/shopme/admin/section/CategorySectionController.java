package com.shopme.admin.section;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.admin.category.CategoryService;
import com.shopme.common.entity.Category;
import com.shopme.common.entity.section.CategorySection;
import com.shopme.common.entity.section.Section;
import com.shopme.common.entity.section.SectionType;
import com.shopme.common.exception.SectionNotFoundException;

@Controller
public class CategorySectionController {

	@Autowired
	private SectionService sectionService;

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/sections/new/category")
	public String showForm(Model model) {
		Section section = new Section(true, SectionType.CATEGORY);
		
		List<Category> listCategories = categoryService.listAll();
		
		model.addAttribute("listCategories", listCategories);		
		model.addAttribute("section", section);
		model.addAttribute("pageTitle", "Add Category Section");
		
		return "sections/category_section_form";
	}	
	
	@PostMapping("/sections/save/category")
	public String saveSection(Section section, HttpServletRequest request, RedirectAttributes ra) {
		addCategoriesToSection(section, request);
		sectionService.saveSection(section);
		ra.addFlashAttribute("message", "The section of type Category has been saved successfully.");
		
		return "redirect:/sections";
	}	
	
	private void addCategoriesToSection(Section section, HttpServletRequest request) {
		String[] IDs = request.getParameterValues("chosenCategories");
		
		if (IDs != null && IDs.length > 0) {
			for (int i = 0; i < IDs.length; i++) {
				String[] arrayIds = IDs[i].split("-");
				
				CategorySection categorySection = new CategorySection();
				
				Integer categorySectionId = Integer.valueOf(arrayIds[1]);
				if (categorySectionId > 0) {
						categorySection.setId(categorySectionId);
				}
					
				categorySection.setCategoryOrder(i);
				Integer categoryId = Integer.valueOf(arrayIds[0]);
				
				categorySection.setCategory(new Category(categoryId));
				section.addCategorySection(categorySection);
				
			}
		}		
	}
	
	@GetMapping("/sections/edit/Category/{id}")
	public String editSection(@PathVariable(name = "id") Integer id, RedirectAttributes ra,
			Model model) {
		try {
			Section section = sectionService.getSection(id);
			List<Category> listCategories = categoryService.listAll();
			
			model.addAttribute("listCategories", listCategories);			
			model.addAttribute("section", section);
			model.addAttribute("pageTitle", "Edit Category Section (ID: " + id + ")");
			
			return "sections/category_section_form";
			
		} catch (SectionNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			return "redirect:/sections";		
		}
		
	}
}
