package com.app.custom_exceptions;

@SuppressWarnings("serial")
public class ProductHandlingException extends RuntimeException {

	public ProductHandlingException(String msg) {
		super(msg);
	}
}
