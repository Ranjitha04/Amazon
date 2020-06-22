package com.example.amazon.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.amazon.dao.ProductDao;
import com.example.amazon.dto.ProductResponseDto;
import com.example.amazon.dto.ProductsResponseDto;
import com.example.amazon.exception.ProductNotFoundException;
import com.example.amazon.model.Product;
import com.example.amazon.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public List<ProductsResponseDto> getAllProducts() {
		Iterable<Product> products = productDao.findAll();

		List<ProductsResponseDto> responseProductList = new ArrayList<ProductsResponseDto>();
		products.forEach(product -> responseProductList.add(getProductDto(product)));
		return responseProductList;
	}

	@Override
	public ProductsResponseDto getProductDto(Product product) {
		ProductsResponseDto responseDto = new ProductsResponseDto();
		BeanUtils.copyProperties(product, responseDto);
		return responseDto;
	}

	@Override
	public ProductResponseDto getProductById(int productId) {
		Optional<Product> productOptional = productDao.findById(productId);
		ProductResponseDto responseDto = new ProductResponseDto();
		if (productOptional.isPresent()) {
			BeanUtils.copyProperties(productOptional.get(), responseDto);
			return responseDto;
		}
		throw new ProductNotFoundException("There Exists No Product With The Given Id: " + productId);

	}

	@Override
	public List<ProductsResponseDto> getProductByName(String productName) {
		Optional<List<Product>> productsOptional = productDao.findByProductName(productName);
		if (productsOptional.isPresent()) {
			return productsOptional.get().stream().map(product -> getProductDto(product)).collect(Collectors.toList());
		}
		throw new ProductNotFoundException("There Exists No Product By Name: " + productName);
	}

}
