package com.example.amazon.exception;

public class ProductNotFoundException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;

	public ProductNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public ProductNotFoundException() {
		super();
	}
	
}
