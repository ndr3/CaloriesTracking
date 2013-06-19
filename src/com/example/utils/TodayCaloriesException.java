package com.example.utils;

public class TodayCaloriesException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TodayCaloriesException(){}

	public TodayCaloriesException(Exception msg){
		super(msg);
	}
	
	public TodayCaloriesException(String message) {
        super(message);
    }

    public TodayCaloriesException(String message, Throwable cause) {
        super(message, cause);
    }
}
