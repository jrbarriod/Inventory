package com.inventory.api.rest.dao;

import org.springframework.data.repository.CrudRepository;

import com.inventory.api.rest.models.Article;

public interface IArticleDao extends CrudRepository<Article, Long>{

}
