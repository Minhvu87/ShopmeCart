package com.shopme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopme.category.CategoryService;
import com.shopme.common.entity.Category;
import com.shopme.common.entity.section.Section;
import com.shopme.common.entity.section.SectionType;
import com.shopme.section.SectionService;

@Controller
public class MainController {
	@Autowired private CategoryService categoryService;
	@Autowired private SectionService sectionService;
	
	@GetMapping("")
	public String viewHomePage(Model model) {
		List<Section> listSections = sectionService.listEnabledSections();
		model.addAttribute("listSections", listSections);
		
		if (hasAllCategoriesSection(listSections)) {
			List<Category> listCategories = categoryService.listNoChildrenCategories();
			model.addAttribute("listCategories", listCategories);
		}
		
		return "index";
	}

	private boolean hasAllCategoriesSection(List<Section> listSections) {
		for (Section section : listSections) {
			if (section.getType().equals(SectionType.ALL_CATEGORIES)) {
				return true;
			}
		}
		
		return false;
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		
		return "redirect:/";
	}
}
