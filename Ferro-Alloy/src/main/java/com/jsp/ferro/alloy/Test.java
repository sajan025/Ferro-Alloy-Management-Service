package com.jsp.ferro.alloy;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		convertBcryptPassowrd();
	}
	 private static void convertBcryptPassowrd() {
		  org.springframework.security.crypto.password.PasswordEncoder encoder = new
		  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder(); for (int
		  i = 0; i < 5; i++) { 
			  String passwd = encoder.encode("12345");
		  System.out.println("crypto.password:::: "+passwd); // print hash
		  System.out.println(encoder.matches("12345", passwd)); }
		  
		  }

}
