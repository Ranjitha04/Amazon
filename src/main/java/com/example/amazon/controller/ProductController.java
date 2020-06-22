package com.example.amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.amazon.dto.ProductResponseDto;
import com.example.amazon.dto.ProductsResponseDto;
import com.example.amazon.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/products")
	public List<ProductsResponseDto> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/products/{productId}")
	public ProductResponseDto getProductById(@PathVariable("productId") int productId) {
		return productService.getProductById(productId);
	}
	
	@GetMapping("/products/name")
	public List<ProductsResponseDto> getProductByName(@RequestParam("productName") String productName) {
		return productService.getProductByName(productName);
		
	}
	
}
