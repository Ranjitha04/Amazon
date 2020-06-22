package com.example.amazon.exception;

public class OrdersNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

	public OrdersNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public OrdersNotFoundException() {
		super();
	}
}
