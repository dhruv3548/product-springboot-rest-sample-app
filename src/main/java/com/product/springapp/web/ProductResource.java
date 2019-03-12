package com.product.springapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.product.springapp.domain.Product;
import com.product.springapp.service.ProductService;

/*
 * GET /products ---> json with all products
 * GET /products/id ----> json with one product
 * POST /products ----> confirmation of creatino and uri to newly created product
*/

@RestController
public class ProductResource {

	@Autowired
	ProductService service;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> allProducts() {
		List<Product> all = service.findAll();
		return all;
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getById(@PathVariable("id") long id) {
		Product p = service.findById(id);
		if (p == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Product> addNew(@RequestBody Product p, UriComponentsBuilder ucb) {
		try {
			long id = service.addNewProduct(p.getName(), p.getPrice(), p.getQoh());
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucb.path("/products/{id}").buildAndExpand(id).toUri());
			return new ResponseEntity<Product>(headers, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
	}
}
