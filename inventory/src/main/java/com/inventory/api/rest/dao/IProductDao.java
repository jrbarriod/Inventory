package com.inventory.api.rest.dao;

import org.springframework.data.repository.CrudRepository;

import com.inventory.api.rest.models.Product;

public interface IProductDao extends CrudRepository<Product, Long>{

	
	
}
