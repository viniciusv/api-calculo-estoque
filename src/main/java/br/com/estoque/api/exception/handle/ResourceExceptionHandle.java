package br.com.estoque.api.exception.handle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.estoque.api.service.exceptions.ProdutoNotFoundException;
import br.com.estoque.api.service.exceptions.ValidationNotFoundException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ResourceExceptionHandle {
	
	@ExceptionHandler(ValidationNotFoundException.class)
	public ResponseEntity<StandardError> validationNotFoundException(ValidationNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		log.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);		
	}
	
	@ExceptionHandler(ProdutoNotFoundException.class)
	public ResponseEntity<StandardError> produtoNotFoundException(ProdutoNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		log.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);		
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<StandardError> methodArgumentTypeMismatchException(NumberFormatException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), System.currentTimeMillis());
		
		log.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);		
	}

}
