package com.example.amazon.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.amazon.dto.Credentials;
import com.example.amazon.dto.OrdersResponseDto;
import com.example.amazon.dto.UserRequestDto;
import com.example.amazon.service.OrderService;
import com.example.amazon.service.ProductService;


/**
 * @author ranjitha-r
 *
 */

@RestController
public class UserController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/users")
	public ResponseEntity<String> register(@RequestBody UserRequestDto userRequestDto) {	
		try {
			return new ResponseEntity<String>(restTemplate.postForObject(new URI("http://localhost:8080/users"), userRequestDto, String.class), HttpStatus.OK);
		} catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return new ResponseEntity<String>("Registration Failed", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/users/login")
	public String login(@Valid @RequestBody Credentials credentials) {
		/*
		 * boolean isExists = userService.authenticate(credentials.getUsername(),
		 * credentials.getPassword());
		 * 
		 * if(isExists) return "login success";
		 */
		 return "invalid credentials";
		 
	}
	
	
	@GetMapping("/users/{userId}/orders")
	public ResponseEntity<List<OrdersResponseDto>> getOrdersHistory(@PathVariable("userId") int userId) {
		return new ResponseEntity<List<OrdersResponseDto>>(orderService.getOrdersHistory(userId),HttpStatus.OK);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException ex) {
		Map<String,String> errorMessage = new HashMap<String,String>();
				ex.getBindingResult().getFieldErrors().forEach(error -> errorMessage.put("message",error.getDefaultMessage()));
	   return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
	
	}
}
