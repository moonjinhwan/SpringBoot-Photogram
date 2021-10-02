package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.handler.ex.CustomValidationException2;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.auth.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHanlder {
	/*
	@ExceptionHandler(CustomValidationException.class)
	public CMRespDto<?> validationException(CustomValidationException e) {
		return new CMRespDto<>(-1, e.getMessage(), e.getErrorMap());
	}
	*/
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		if(e.getErrorMap() == null) {
			return Script.back(e.getMessage());
		}else {
			return Script.back(e.getErrorMap().toString());
		}
	}
	
	@ExceptionHandler(CustomValidationException2.class)
	public ResponseEntity<?> validationException(CustomValidationException2 e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()) , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomException.class)
	public String exception(CustomException e) {
		return Script.back(e.getMessage());
	}
}
