package com.lecoffretderachel.ordersmanager;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lecoffretderachel.ordersmanager.model.Product;
import com.lecoffretderachel.ordersmanager.service.ProductService;

public class App {

	public static void main(String[] args) {
		System.out.println("load context");
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Product em = new Product();
		em.setName("John");
		em.setColor("blue");
		ProductService emService = (ProductService) context.getBean("productService");
		emService.persistProduct(em);
		Integer id = em.getId();
		System.out.println("Color :" + emService.findProductById(id).getColor());		
		em.setColor("red");
		emService.updateProduct(em);
		System.out.println("Updated color :" + emService.findProductById(id).getColor());
		emService.deleteProduct(em);
		context.close();
	}
}
