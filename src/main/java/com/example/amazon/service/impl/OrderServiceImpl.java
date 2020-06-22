package com.example.amazon.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.amazon.dao.OrderDao;
import com.example.amazon.dao.ProductDao;
import com.example.amazon.dto.OrdersResponseDto;
import com.example.amazon.exception.OrdersNotFoundException;
import com.example.amazon.model.Orders;
import com.example.amazon.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	ProductDao productDao;

	@Override
	public String placeOrder(int productId, int userId, int quantity) {

		Orders order = new Orders();
		order.setProductId(productId);
		order.setQuantity(quantity);
		order.setLocalDate(LocalDate.now());
		order.setTotalCost(productDao.findPriceByProductId(productId) * quantity);
		Orders orderPlaced = orderDao.save(order);

		if (orderPlaced.getOrderId() <= 0)
			return "Couldn't Place Order Please Try Again After Sometime";
		return "Order Placed Successfully. Your Order id: " + orderPlaced.getOrderId();
	}

	@Override
	public List<OrdersResponseDto> getOrdersHistory(int userId) {
		Optional<List<Orders>> ordersOptional = orderDao.findAllByUserId(userId);

		if (ordersOptional.isPresent()) {
			List<Orders> orders = ordersOptional.get();
			return orders.stream().map(order -> getOrdersResponse(order)).collect(Collectors.toList());
		}
		throw new OrdersNotFoundException("No Orders found...Please do shoppig");
	}

	private OrdersResponseDto getOrdersResponse(Orders order) {
		OrdersResponseDto responseDto = new OrdersResponseDto();
		BeanUtils.copyProperties(order, responseDto);
		responseDto.setProductName(productDao.findProductNameByProductId(order.getProductId()));
		return responseDto;
	}
}
