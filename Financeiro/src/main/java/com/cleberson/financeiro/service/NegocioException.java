package com.cleberson.financeiro.service;

/**
 * Exception que representará erros de negócio
 * @author Cleberson
 *
 */
public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NegocioException(String message) {
		super(message);
	}

}
