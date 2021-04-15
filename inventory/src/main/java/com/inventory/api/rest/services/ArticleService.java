package com.inventory.api.rest.services;

import java.io.InputStreamReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inventory.api.rest.dao.IArticleDao;
import com.inventory.api.rest.dao.IProductDao;
import com.inventory.api.rest.models.Article;

@Service
public class ArticleService implements IArticleService{

	
	@Autowired
	private IArticleDao articleDao;
	@Autowired
	private IProductDao productDao;


	@Override
	@SuppressWarnings("unchecked")
	public void loadArticles(MultipartFile file) {
		
		productDao.deleteAll();
		articleDao.deleteAll();
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new InputStreamReader(file.getInputStream()));
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray articlesJSON = (JSONArray) jsonObject.get("inventory");
			
			Iterator<JSONObject> it =  articlesJSON.iterator();
			JSONObject articleJSON = null;
			Article article = null;
			
			while(it.hasNext()) {
				articleJSON = it.next();
				article = new Article();
				article.setArticleId(Long.parseLong(articleJSON.get("art_id").toString()));
				article.setName(articleJSON.get("name").toString());
				article.setStock(Long.parseLong(articleJSON.get("stock").toString()));
				
				articleDao.save(article);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
