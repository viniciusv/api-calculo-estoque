package br.com.ubs.api.service.exceptions;

public class ValidationNotFoundException extends RuntimeException
{

	private static final long serialVersionUID = 3352783180971384950L;
	
	public ValidationNotFoundException(String msg) {
		super(msg);
	}
	
	public ValidationNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
