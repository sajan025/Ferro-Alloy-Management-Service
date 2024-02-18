package com.jsp.ferro.alloy.exception;

import org.springframework.http.HttpStatus;
/**
 * @author Sajan Yadav
 * @apiNote Used for Custom Exception
 * @Date 12 Sept 2023
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
