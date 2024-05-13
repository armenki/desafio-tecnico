package com.desafio.tecnico.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + " " + error.getDefaultMessage()).collect(Collectors.toList());
		String errorMessage = String.join("; ", errorMessages);
		String jsonMessage = "{\"mensaje\": \"" + errorMessage + "\"}";
		return ResponseEntity.badRequest().body(jsonMessage);
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
		String errorMessage = ex.getMessage();
		String jsonMessage = "{\"mensaje\": \"" + errorMessage + "\"}";
		return ResponseEntity.badRequest().body(jsonMessage);
	}
}
