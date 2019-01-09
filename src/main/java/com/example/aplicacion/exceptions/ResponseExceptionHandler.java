package com.example.aplicacion.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
//permanece escuchando y atrapa la excepcion que se a escrito
@ControllerAdvice
public class ResponseExceptionHandler {
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> conflict(DataIntegrityViolationException exc){
		ResponseException response=new ResponseException(HttpStatus.CONFLICT.value(), "Hubo un conflicto ", exc.getMessage());
		return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
	}

}
