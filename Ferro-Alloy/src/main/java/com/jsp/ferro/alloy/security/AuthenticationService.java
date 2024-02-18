package com.jsp.ferro.alloy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jsp.ferro.alloy.exception.UserCustomException;
import com.jsp.ferro.alloy.model.User;
import com.jsp.ferro.alloy.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserService userService;
    public Integer getCurrentCustomerId(){

    	User jwtUserDetails = getCurrentJwtUserDetails();

    	Integer jwtUserDetailsId = null;
        if (jwtUserDetails != null){
            jwtUserDetailsId = jwtUserDetails.getUserPkId();
        }

        return jwtUserDetailsId;
    }
	
	@SuppressWarnings("unused")
	private User getCurrentJwtUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal() instanceof UserDetails);
        String userName = null;
        if (authentication != null ){
        	userName = ((UserDetails) authentication.getPrincipal()).getUsername();
        }else {
        	throw new UserCustomException("Somthing went wrong.");
        }
        
        User user =userService.findUserByUserName(userName);
        if(user==null) throw new UserCustomException("User not found.");
        return  user;
    }
}
