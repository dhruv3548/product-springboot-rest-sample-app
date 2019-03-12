package com.product.springapp.service;

import java.util.List;

import com.product.springapp.domain.Product;

public interface ProductService {

	public long addNewProduct(String name, float price, int qoh);

	public Product findById(long id);

	public List<Product> findAll();
}
