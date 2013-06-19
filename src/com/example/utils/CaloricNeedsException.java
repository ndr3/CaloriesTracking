package com.example.utils;

public class CaloricNeedsException extends Exception {
private static final long serialVersionUID = 1L;
	
	public CaloricNeedsException(){}

	public CaloricNeedsException(Exception msg){
		super(msg);
	}
	
	public CaloricNeedsException(String message) {
        super(message);
    }

    public CaloricNeedsException(String message, Throwable cause) {
        super(message, cause);
    }
}
