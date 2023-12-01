package com.shopme.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopme.common.entity.Article;

@Controller
public class ArticleController {

	@Autowired
	private ArticleRepository repo;
	
	@GetMapping("/a/{alias}")
	public String viewArticle(@PathVariable("alias") String alias, Model model) {
		Article article = repo.findByAlias(alias);

		if (article == null ) {
			return "error/404";
		}
		
		model.addAttribute("article", article);
		return "article";
	}
}
