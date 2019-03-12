package com.product.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProductApp {

	public static void main(String[] args) {
		ApplicationContext springContainer = SpringApplication.run(ProductApp.class, args);
//		System.out.println("Context created.....");
//		ProductConsoleUI ui = (ProductConsoleUI) springContainer.getBean("uiInstance");
//		ui.displayAll();
	}
}
