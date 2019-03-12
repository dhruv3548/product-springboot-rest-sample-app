package com.product.springapp.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.product.springapp.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	public List<Product> findByNameLike(String name);

	public List<Product> findAll();
}
