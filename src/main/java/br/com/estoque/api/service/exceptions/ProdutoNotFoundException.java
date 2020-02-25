package br.com.estoque.api.service.exceptions;

public class ProdutoNotFoundException extends RuntimeException
{

	private static final long serialVersionUID = 3352783180971384950L;
	
	public ProdutoNotFoundException(String msg) {
		super(msg);
	}
	
	public ProdutoNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
