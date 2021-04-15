package com.inventory.api.rest.dao;

import org.springframework.data.repository.CrudRepository;

import com.inventory.api.rest.models.ProductsArticle;
import com.inventory.api.rest.models.ProductsArticlePK;

public interface IProductsArticlesDao extends CrudRepository<ProductsArticle, ProductsArticlePK>{

}
