package br.com.api.exception;

public class ApiException extends RuntimeException {
	
	private static final long serialVersionUID = -6649784710273046684L;

	public ApiException(String s) {
		super(s);
	}

	public ApiException(String s, Throwable throwable) {
		super(s, throwable);
	}
}