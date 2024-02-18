package com.jsp.ferro.alloy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ferro.alloy.constants.ResponseCode;
import com.jsp.ferro.alloy.exception.CustomException;
import com.jsp.ferro.alloy.repository.UserRepository;
import com.jsp.ferro.alloy.response.ServerResponse;
import com.jsp.ferro.alloy.security.JwtTokenProvider;
import com.jsp.ferro.alloy.service.MyUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Sajan Yadav
 * @apiNote Used for authentication
 * @Date 12 Sept 2023
 */

@Tag(name = "AuthController", description = "For Authentication & Authorization")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private MyUserDetailsService userService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/signin")
	@Operation(tags = "AuthController", summary = " Authenticates user and returns its JWT token.", description = "Gets JWT token.")
	public ResponseEntity<ServerResponse> login(@RequestParam String username, @RequestParam String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		final UserDetails userDetails = userService.loadUserByUsername(username);
		String jwtToken = jwtTokenProvider.createToken(username, userRepository.findByUserName(username).getUserRole());

		ServerResponse resp = new ServerResponse();
		resp.setStatus(ResponseCode.SUCCESS_CODE);
		resp.setMessage(ResponseCode.SUCCESS_MESSAGE);
		resp.setAuthToken(jwtToken);
		resp.setUsername(username);
		if (userDetails != null
				&& userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			resp.setUserType("ADMIN");
		} else if (userDetails != null
				&& userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_QUALITY"))) {
			resp.setUserType("QUALITY");
		} else if (userDetails != null
				&& userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SMS"))) {
			resp.setUserType("SMS");
		} else if (userDetails != null
				&& userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_FINANCE"))) {
			resp.setUserType("FINANCE");
		}

		return new ResponseEntity<ServerResponse>(resp, HttpStatus.OK);

	}

	@GetMapping(value = "/logout")
	public Boolean logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			return true;
		}
		return false;
	}

	@GetMapping(value = "/refresh-token/{username}")
	public ResponseEntity<ServerResponse> refreshToken(@PathVariable String username, HttpServletRequest request,
			HttpServletResponse response) {
		String jwtToken = jwtTokenProvider.createToken(username, userRepository.findByUserName(username).getUserRole());
		ServerResponse resp = new ServerResponse();
		resp.setStatus(ResponseCode.SUCCESS_CODE);
		resp.setMessage(ResponseCode.SUCCESS_MESSAGE);
		resp.setAuthToken(jwtToken);
		resp.setUsername(username);

		return new ResponseEntity<ServerResponse>(resp, HttpStatus.OK);
	}
}
