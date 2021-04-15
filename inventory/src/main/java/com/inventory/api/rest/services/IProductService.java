package com.inventory.api.rest.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface IProductService {

	public void loadProducts(MultipartFile file);
	
	public List<Map<String, Object>> getInventory(); 
	
	public void removeProduct(Long id);
}
