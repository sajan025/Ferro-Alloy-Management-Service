package com.jsp.ferro.alloy.response;

import java.util.List;

/**
 * @author Sajan Yadav
 * @apiNote Custom Server response
 * @Date 12 Sept 2023
 */
public class ServerResponse {
	private String username;
	private String status;
	private String message;
	private String authToken;
	private String userType;
	

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ServerResponse() {
		super();
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
