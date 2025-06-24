package com.jobportal.Job.Portal.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForLocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler(Exception.class)//this tells that this is exception handler
	public ResponseEntity<ErrorInfo> generalException(Exception exception)// here also we'll use responseentity to give response like we did in api, but here if exception comes then its response will be given
	{
		ErrorInfo error=new ErrorInfo(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now());
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
	public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception)
	{
		String msg="";
		if(exception instanceof MethodArgumentNotValidException manvException)
		{
			msg=manvException.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
		}
		else //doosra wala exception
		{
			ConstraintViolationException cvException=(ConstraintViolationException)exception;
			msg=cvException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
		}
		ErrorInfo error=new ErrorInfo(msg,HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
}
