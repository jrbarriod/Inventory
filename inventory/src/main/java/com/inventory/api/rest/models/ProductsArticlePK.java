package com.inventory.api.rest.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the products_articles database table.
 * 
 */
@Embeddable
public class ProductsArticlePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="product_id", insertable=false, updatable=false)
	private Long productId;

	@Column(name="article_id", insertable=false, updatable=false)
	private Long articleId;

	public ProductsArticlePK() {
	}
	public Long getProductId() {
		return this.productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getArticleId() {
		return this.articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductsArticlePK)) {
			return false;
		}
		ProductsArticlePK castOther = (ProductsArticlePK)other;
		return 
			this.productId.equals(castOther.productId)
			&& this.articleId.equals(castOther.articleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.productId.hashCode();
		hash = hash * prime + this.articleId.hashCode();
		
		return hash;
	}
}