package com.inventory.api.rest.services;

import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inventory.api.rest.dao.IArticleDao;
import com.inventory.api.rest.dao.IProductDao;
import com.inventory.api.rest.dao.IProductsArticlesDao;
import com.inventory.api.rest.models.Article;
import com.inventory.api.rest.models.Product;
import com.inventory.api.rest.models.ProductsArticle;
import com.inventory.api.rest.models.ProductsArticlePK;

@Service
public class ProductService implements IProductService{

	@Autowired
	private IProductDao productDao;

	@Autowired
	private IProductsArticlesDao productsArticlesDao;

	@Autowired
	private IArticleDao articleDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public void loadProducts(MultipartFile file) {
		
		productDao.deleteAll();
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new InputStreamReader(file.getInputStream()));
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray productsJSON = (JSONArray) jsonObject.get("products");
			
			JSONArray productArticlesJSON = null;
			
			Iterator<JSONObject> it =  productsJSON.iterator();
			JSONObject productJSON = null;
			JSONObject productsArticleJSON = null;
			Product product = null;
			ProductsArticle productsArticle = null;
			ProductsArticlePK productsArticlePK = null;
			Iterator<JSONObject> itArticles = null; 
			
			while(it.hasNext()) {
				productJSON = it.next();
				product = new Product();
				product.setName(productJSON.get("name").toString());
				
				product = productDao.save(product);
				
				productArticlesJSON = (JSONArray) productJSON.get("contain_articles");
				
				itArticles= productArticlesJSON.iterator();
				while(itArticles.hasNext()) {
					productsArticleJSON = itArticles.next();
					
					productsArticlePK = new ProductsArticlePK();
					productsArticlePK.setArticleId(Long.parseLong(productsArticleJSON.get("art_id").toString()));
					productsArticlePK.setProductId(product.getProductId());
					
					productsArticle = new ProductsArticle();
					productsArticle.setId(productsArticlePK);
					productsArticle.setAmount(Long.parseLong(productsArticleJSON.get("amount_of").toString()));
					productsArticlesDao.save(productsArticle);
					
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Map<String, Object>> getInventory() {
		
		StringBuilder sb = new StringBuilder("select p.product_id as product_id, p.name as name, m.available as available ");
		sb.append("from (select pa.product_id, floor(min(coalesce(a.stock, 0)/pa.amount)) as available ");   
		sb.append("from products_articles pa  "); 
		sb.append("left join articles a  ");
		sb.append("on (pa.article_id = a.article_id)  ");
		sb.append("group by pa.product_id) as m  ");
		sb.append("inner join products p  ");
		sb.append("on (p.product_id = m.product_id) ");
		
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sb.toString());
		return result;
	}

	@Override
	public void removeProduct(Long id) {
	
		Product product = productDao.findById(id).orElseThrow();
		List<ProductsArticle> list = product.getProductsArticles();
		ProductsArticle productArticle = null;
		Article article = null;
		
		Iterator<ProductsArticle> it = list.iterator();
		
		while(it.hasNext()) {
			productArticle = it.next();
			article = productArticle.getArticle();
			if(article.getStock()<productArticle.getAmount())
				return;
		}
		
		it = list.iterator();
		
		while(it.hasNext()) {
			productArticle = it.next();
			article = productArticle.getArticle();
			article.setStock(article.getStock()-productArticle.getAmount());
			articleDao.save(article);
		}
	}
	
	

	
}
