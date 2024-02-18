package com.jsp.ferro.alloy.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.ferro.alloy.model.User;

/**
 * @author Sajan
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
    User findByEmail(String email);
    User findByUserName(String userName);
   
    
}