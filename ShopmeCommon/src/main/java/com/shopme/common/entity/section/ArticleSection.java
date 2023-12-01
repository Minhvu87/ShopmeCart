package com.shopme.common.entity.section;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shopme.common.entity.Article;
import com.shopme.common.entity.IdBasedEntity;

@Entity
@Table(name = "sections_articles")
public class ArticleSection extends IdBasedEntity{

	@Column(name = "article_order")
	private int articleOrder;

	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;

	public int getArticleOrder() {
		return articleOrder;
	}

	public void setArticleOrder(int articleOrder) {
		this.articleOrder = articleOrder;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}
