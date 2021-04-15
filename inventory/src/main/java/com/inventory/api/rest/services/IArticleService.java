package com.inventory.api.rest.services;

import org.springframework.web.multipart.MultipartFile;


public interface IArticleService {
	
	public void loadArticles(MultipartFile file);

}