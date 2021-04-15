package com.inventory.api.rest.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private Long productId;

	private String name;

	//bi-directional many-to-one association to ProductsArticle
	@OneToMany(mappedBy="product")
	private List<ProductsArticle> productsArticles;

	public Product() {
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductsArticle> getProductsArticles() {
		return this.productsArticles;
	}

	public void setProductsArticles(List<ProductsArticle> productsArticles) {
		this.productsArticles = productsArticles;
	}

	public ProductsArticle addProductsArticle(ProductsArticle productsArticle) {
		getProductsArticles().add(productsArticle);
		productsArticle.setProduct(this);

		return productsArticle;
	}

	public ProductsArticle removeProductsArticle(ProductsArticle productsArticle) {
		getProductsArticles().remove(productsArticle);
		productsArticle.setProduct(null);

		return productsArticle;
	}

}