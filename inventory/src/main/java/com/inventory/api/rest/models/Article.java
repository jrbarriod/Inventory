package com.inventory.api.rest.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the articles database table.
 * 
 */
@Entity
@Table(name="articles")
@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="article_id")
	private Long articleId;

	private String name;

	private Long stock;

	//bi-directional many-to-one association to ProductsArticle
	@OneToMany(mappedBy="article")
	private List<ProductsArticle> productsArticles;

	public Article() {
	}

	public Long getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStock() {
		return this.stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public List<ProductsArticle> getProductsArticles() {
		return this.productsArticles;
	}

	public void setProductsArticles(List<ProductsArticle> productsArticles) {
		this.productsArticles = productsArticles;
	}

	public ProductsArticle addProductsArticle(ProductsArticle productsArticle) {
		getProductsArticles().add(productsArticle);
		productsArticle.setArticle(this);

		return productsArticle;
	}

	public ProductsArticle removeProductsArticle(ProductsArticle productsArticle) {
		getProductsArticles().remove(productsArticle);
		productsArticle.setArticle(null);

		return productsArticle;
	}

}