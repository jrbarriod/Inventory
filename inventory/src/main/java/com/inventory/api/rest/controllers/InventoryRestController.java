package com.inventory.api.rest.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.inventory.api.rest.services.IArticleService;
import com.inventory.api.rest.services.IProductService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class InventoryRestController {

	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/inventory")
	public List<Map<String, Object>> getInventory(){
		return productService.getInventory();
	}

	
	@PostMapping("inventory/upload/products")
	public List<Map<String, Object>> uploadProducts(@RequestParam("file") MultipartFile file) {

		productService.loadProducts(file);
		return productService.getInventory();
	}
	
	@PostMapping("inventory/upload/articles")
	public void uploadArticles(@RequestParam("file") MultipartFile file) {
		
		articleService.loadArticles(file);
	}
	
	@PostMapping("/inventory/remove/product/{id}")
	public List<Map<String, Object>> remove(@PathVariable Long id){
		
		productService.removeProduct(id);
		return productService.getInventory();
	}

	
}
