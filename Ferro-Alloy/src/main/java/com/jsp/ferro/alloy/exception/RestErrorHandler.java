package com.jsp.ferro.alloy.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Sajan
 *
 */
@RestControllerAdvice
public class RestErrorHandler {

	  public static void createErrorResponseForServlet(HttpServletResponse httpResponse, HttpStatus status, Object error)
		      throws IOException {

		    ObjectMapper mapper = new ObjectMapper();
		    httpResponse.setContentType("application/json");
		    httpResponse.setStatus(status.value());
		    PrintWriter out = httpResponse.getWriter();
		    mapper.writeValue(out, error.getClass().getSimpleName());
		  }
}
