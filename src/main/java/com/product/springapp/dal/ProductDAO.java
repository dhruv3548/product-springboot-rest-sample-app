package com.product.springapp.dal;

import java.util.List;

import com.product.springapp.domain.Product;

public interface ProductDAO {

	public void save(Product p);

	public Product findById(long id);

	public List<Product> findAll();

}