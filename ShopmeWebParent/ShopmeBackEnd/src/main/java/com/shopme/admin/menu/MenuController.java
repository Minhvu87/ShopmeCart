package com.shopme.admin.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.admin.article.ArticleService;
import com.shopme.admin.menu.MenuService.MenuMoveDirection;
import com.shopme.common.entity.Article;
import com.shopme.common.entity.Menu;
import com.shopme.common.exception.MenuItemNotFoundException;

@Controller
public class MenuController {

private final String defaultRedirectURL = "redirect:/menus";
	
	@Autowired private MenuService menuService;
	@Autowired private ArticleService articleService;
	
	@GetMapping("/menus")
	public String listAll(Model model) {
		List<Menu> listMenuItems = menuService.listAll();
		model.addAttribute("listMenuItems", listMenuItems);
		
		return "menus/menu_items";
	}	

	@GetMapping("menus/new")
	public String newMenu(Model model) {
		List<Article> listArticles = articleService.listArticlesForMenu();
		
		model.addAttribute("menu", new Menu());
		model.addAttribute("listArticles", listArticles);
		model.addAttribute("pageTitle", "Create New Menu Item");
		
		return "menus/menu_form";
	}
	
	@PostMapping("/menus/save")
	public String saveMenu(Menu menu, RedirectAttributes ra) {
		menuService.save(menu);
		ra.addFlashAttribute("message", "The menu item has been saved successfully.");
		
		return defaultRedirectURL;
	}	
	
	@GetMapping("/menus/edit/{id}")
	public String editMenu(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes ra) {
		try {
			Menu menu = menuService.get(id);
			List<Article> listArticles = articleService.listArticlesForMenu();
			
			model.addAttribute("menu", menu);
			model.addAttribute("listArticles", listArticles);
			model.addAttribute("pageTitle", "Edit Menu Item (ID: " + id + ")");
			
			return "menus/menu_form";
		} catch (MenuItemNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());		
			return defaultRedirectURL;
		}
	}	
	
	@GetMapping("/menus/{id}/enabled/{enabledStatus}")
	public String updateMenuEnabledStatus(@PathVariable("id") Integer id, 
			@PathVariable("enabledStatus") String enabledStatus, RedirectAttributes ra) {
		try {
			boolean enabled = Boolean.parseBoolean(enabledStatus);					
			menuService.updateEnabledStatus(id, enabled);		
			
			String updateResult = enabled ? "enabled." : "disabled.";
			ra.addFlashAttribute("message", "The menu item ID " + id + " has been " + updateResult);
		} catch (MenuItemNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}
		
		return defaultRedirectURL;
	}
	
	@GetMapping("/menus/delete/{id}")
	public String deleteMenu(@PathVariable(name = "id") Integer id, RedirectAttributes ra) {
		try {
			menuService.delete(id);
			
			ra.addFlashAttribute("message", "The menu item ID " + id + " has been deleted.");
		} catch (MenuItemNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}
		
		return defaultRedirectURL;
	}	
	
	@GetMapping("/menus/{direction}/{id}")
	public String moveMenu(@PathVariable("direction") String direction, @PathVariable("id") Integer id, 
			RedirectAttributes ra) {
		try {
			MenuMoveDirection moveDirection = MenuMoveDirection.valueOf(direction.toUpperCase());
			menuService.moveMenu(id, moveDirection);
			
			ra.addFlashAttribute("message", "The menu ID " + id + " has been moved up by one position.");
			
		} catch (MenuUnmoveableException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		} catch (MenuItemNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}
		
		return defaultRedirectURL;		
	}
}
