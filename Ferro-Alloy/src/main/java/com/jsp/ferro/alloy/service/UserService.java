package com.jsp.ferro.alloy.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.ferro.alloy.constants.ResponseCode;
import com.jsp.ferro.alloy.exception.CustomException;
import com.jsp.ferro.alloy.model.User;
import com.jsp.ferro.alloy.repository.RoleRepository;
import com.jsp.ferro.alloy.repository.UserRepository;
import com.jsp.ferro.alloy.response.ServerResponse;
import com.jsp.ferro.alloy.security.JwtTokenProvider;

import lombok.AllArgsConstructor;


/**
 * @author Sajan Yadav
 * @apiNote Fatch user by email, name from database
 * @Date 12 Sept 2023
 */
@Service
@AllArgsConstructor
public class UserService {
	
	@Autowired private UserRepository userRepository;
	
   
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

	

    
 

}