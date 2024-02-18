package com.jsp.ferro.alloy.exception;
/**
 * @author Sajan Yadav
 * @apiNote Request Exception
 * @Date 02 Oct 2023
 */
public class RequestException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestException(String message) {super(message);}
}
