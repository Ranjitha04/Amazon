package com.example.amazon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.amazon.dto.OrderRequestDto;
import com.example.amazon.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping("/orders")
	public String  placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
		return orderService.placeOrder(orderRequestDto.getProductId(), orderRequestDto.getUserId(), orderRequestDto.getQuantity());
	}
	
}
