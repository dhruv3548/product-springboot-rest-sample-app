package com.product.springapp.ui;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.springapp.domain.Product;
import com.product.springapp.service.ProductService;

@Component("uiInstance")
public class ProductConsoleUI {

	ProductService service; // = new ProductServiceImpl();

	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}

	public String promptAndRead(String prompt) {
		Scanner s = new Scanner(System.in);
		System.out.println(prompt);
		return s.nextLine();
	}

	public void addNewUser() {
		String name = promptAndRead("Enter name of product: ");
		float price = Float.parseFloat(promptAndRead("Enter price of product: "));
		int qoh = Integer.parseInt(promptAndRead("Eneter number of quantity: "));
		service.addNewProduct(name, price, qoh);
	}

	public void displayProduct() {
		long id = Long.parseLong(promptAndRead("Enter the id of product: "));
		Product p = service.findById(id);
		System.out.println(p);
	}

	public void displayAll() {
		List<Product> all = service.findAll();
		all.forEach(System.out::println);
	}
}
