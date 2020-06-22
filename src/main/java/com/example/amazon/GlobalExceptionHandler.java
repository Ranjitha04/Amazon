package com.example.amazon;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.amazon.exception.InvalidCredentialsException;
import com.example.amazon.exception.OrdersNotFoundException;
import com.example.amazon.exception.ProductNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> invalidCredentialsExceptionHandler(InvalidCredentialsException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> exceptionHandler(ProductNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(OrdersNotFoundException.class)
	public ResponseEntity<String> ordersNotFoundExceptionhandler(OrdersNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.OK);
	}
}
