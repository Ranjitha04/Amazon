package com.example.amazon.service;

import java.util.List;

import com.example.amazon.dto.ProductResponseDto;
import com.example.amazon.dto.ProductsResponseDto;
import com.example.amazon.model.Product;

public interface ProductService {

	List<ProductsResponseDto> getAllProducts();

	ProductResponseDto getProductById(int productId);

	List<ProductsResponseDto> getProductByName(String productName);
    
	ProductsResponseDto getProductDto(Product product);
}
