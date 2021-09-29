package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationException2 extends RuntimeException{
	
	private Map<String, String> errorMap;
	
	public CustomValidationException2(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}
	
	public CustomValidationException2(String message) {
		super(message);
	}
	
	public Map<String, String> getErrorMap(){
		return errorMap;
	}
}