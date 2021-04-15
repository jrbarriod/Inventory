package com.inventory.api.rest.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the products_articles database table.
 * 
 */
@Entity
@Table(name="products_articles")
@NamedQuery(name="ProductsArticle.findAll", query="SELECT p FROM ProductsArticle p")
public class ProductsArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductsArticlePK id;

	private Long amount;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id", insertable =false, updatable = false)
	private Product product;

	//bi-directional many-to-one association to Article
	@ManyToOne
	@JoinColumn(name="article_id", insertable =false, updatable = false)
	private Article article;

	public ProductsArticle() {
	}

	public ProductsArticlePK getId() {
		return this.id;
	}

	public void setId(ProductsArticlePK id) {
		this.id = id;
	}

	public Long getAmount() {
		return this.amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}