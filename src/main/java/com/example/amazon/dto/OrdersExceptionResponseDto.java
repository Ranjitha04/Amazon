package com.example.amazon.dto;

import java.util.List;

public class OrdersExceptionResponseDto {

	private String message;
	List<ProductsResponseDto> productsList;
	
	public OrdersExceptionResponseDto(String message, List<ProductsResponseDto> productsList) {
		super();
		this.message = message;
		this.productsList = productsList;
	}
	public OrdersExceptionResponseDto() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<ProductsResponseDto> getProductsList() {
		return productsList;
	}
	public void setProductsList(List<ProductsResponseDto> productsList) {
		this.productsList = productsList;
	}
	
	
}
