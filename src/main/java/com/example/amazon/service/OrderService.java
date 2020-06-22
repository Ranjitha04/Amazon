package com.example.amazon.service;

import java.util.List;

import com.example.amazon.dto.OrdersResponseDto;

public interface OrderService {

	List<OrdersResponseDto> getOrdersHistory(int userId);

	String placeOrder(int productId, int userId, int quantity);

}
