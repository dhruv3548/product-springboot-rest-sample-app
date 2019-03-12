package com.product.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.springapp.dal.ProductRepository;
import com.product.springapp.domain.Product;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository dao;

	@Override
	public long addNewProduct(String name, float price, int qoh) {
		if (price < 1 || qoh < 10) {
			throw new IllegalArgumentException("Price too low of insufficient quantity");
		}
		if (price * qoh < 1000) {
			throw new IllegalArgumentException("Insufficinet value of product");
		}

		Product p = new Product(name, price, qoh);
		Product saved = dao.save(p);
		return saved.getId();
	}

	@Override
	public Product findById(long id) {
		return dao.findOne(id);
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

}
