package com.greatlearning.employee.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error HandleInvalidEmployeeId(IllegalArgumentException exception)
	{
		System.out.println("Invalid Employee Id"+exception.getMessage());
		Error error=Error
				         .builder()
				         .code(100)
				         .message(exception.getMessage())
				         .build();
		return error;
	}  
}
@Builder
@AllArgsConstructor
@Getter
class Error
{
	private int code;
	private String message;
}

