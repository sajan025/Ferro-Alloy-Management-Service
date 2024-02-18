package com.jsp.ferro.alloy.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.jsp.ferro.alloy.exception.UserCustomException;
import com.jsp.ferro.alloy.model.User;
import com.jsp.ferro.alloy.model.UserRole;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author Sajan Yadav
 * @Date 15 Sept 2023
 * 
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) {
    	//convertBcryptPassowrd();
        User user = userService.findUserByUserName(userName);
        if (user == null) {
            throw new UserCustomException("User '" + userName + "' not found");
        }
        List<GrantedAuthority> authorities = getUserAuthority(user);
        return buildUserForAuthentication(user, authorities);
    }

	private List<GrantedAuthority> getUserAuthority(User userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (UserRole role : userRoles.getUserRole()) {
            roles.add(new SimpleGrantedAuthority(role.getRole().getRoleName()));
        }
        return new ArrayList<>(roles);
    }
    
  

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
        		true, true, true, true, authorities);
    }
    
	 
}
