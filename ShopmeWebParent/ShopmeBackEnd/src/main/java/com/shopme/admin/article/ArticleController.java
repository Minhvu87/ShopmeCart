package com.shopme.admin.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.admin.paging.PagingAndSortingHelper;
import com.shopme.admin.paging.PagingAndSortingParam;
import com.shopme.admin.security.ShopmeUserDetails;
import com.shopme.common.entity.Article;
import com.shopme.common.exception.ArticleNotFoundException;

@Controller
public class ArticleController {

private String defaultRedirectURL = "redirect:/articles/page/1?sortField=title&sortDir=asc";
	
	@Autowired private ArticleService service;
	
	@GetMapping("/articles")
	public String listFirstPage(Model model) {
		return defaultRedirectURL;
	}
	
	@GetMapping("/articles/page/{pageNum}")
	public String listByPage(@PagingAndSortingParam(moduleURL = "/articles", listName = "listArticles") PagingAndSortingHelper helper, 
						@PathVariable(name = "pageNum") int pageNum) {
		service.listByPage(pageNum, helper);
		return "articles/articles";
	}	
	
	@GetMapping("/articles/new")
	public String newArticle(Model model) {
		model.addAttribute("article", new Article());
		model.addAttribute("pageTitle", "Create New Article");
		
		return "articles/article_form";
	}	
	
	@PostMapping("/articles/save")
	public String saveArticle(Article article, RedirectAttributes ra, 
			@AuthenticationPrincipal ShopmeUserDetails userDetails) {
		
		service.save(article, userDetails.getUser());
		
		ra.addFlashAttribute("message", "The article has been saved successfully.");
		
		return defaultRedirectURL;
	}
	
	@GetMapping("/articles/edit/{id}")
	public String editArticle(@PathVariable(name = "id") Integer id, Model model,
			RedirectAttributes ra) {
		try {
			Article article = service.get(id);
			model.addAttribute("article", article);
			model.addAttribute("pageTitle", "Edit Article (ID: " + id + ")");
			
			return "articles/article_form"; 
			
		} catch (ArticleNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			
			return defaultRedirectURL;
		}		
	}	
	
	@GetMapping("/articles/detail/{id}")
	public String viewArticle(@PathVariable(name = "id") Integer id, RedirectAttributes ra,  Model model) {
		try {
			Article article = service.get(id);
			model.addAttribute("article", article);
			
			return "articles/article_detail_modal";
			
		} catch (ArticleNotFoundException ex) {
			ra.addFlashAttribute("message", "Could not find any article with ID " + id);
			return defaultRedirectURL;
		}		
	}
	
	@GetMapping("/articles/{id}/enabled/{publishStatus}")
	public String publishArticle(@PathVariable("id") Integer id, 
			@PathVariable("publishStatus") String publishStatus, RedirectAttributes ra) {
		try {
			boolean published = Boolean.parseBoolean(publishStatus);					
			service.updatePublishStatus(id, published);		
			
			String publishResult = published ? "published." : "unpublished.";
			ra.addFlashAttribute("message", "The article ID " + id + " has been " + publishResult);
		} catch (ArticleNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}
		
		return defaultRedirectURL;
	}
	
	@GetMapping("/articles/delete/{id}")
	public String deleteArticle(@PathVariable(name = "id") Integer id, RedirectAttributes ra) {
		try {
			service.delete(id);
			ra.addFlashAttribute("message", "The article ID " + id + " has been deleted.");
		} catch (ArticleNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}
		
		return defaultRedirectURL;
	}
}
