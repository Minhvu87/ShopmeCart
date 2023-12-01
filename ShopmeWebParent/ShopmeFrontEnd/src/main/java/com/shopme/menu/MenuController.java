package com.shopme.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopme.common.entity.Article;
import com.shopme.common.exception.MenuItemNotFoundException;

@Controller
public class MenuController {

    @Autowired private MenuService service;
	
	@GetMapping("/m/{alias}")
	public String viewMenu(@PathVariable(name = "alias") String alias, Model model) {
		try {
			Article article = service.getArticleBoundToMenu(alias);
			model.addAttribute("pageTitle", article.getTitle());
			model.addAttribute("article", article);
			
		} catch (MenuItemNotFoundException ex) {
			return "error/404";
		}
		
		return "article";
	}
}
