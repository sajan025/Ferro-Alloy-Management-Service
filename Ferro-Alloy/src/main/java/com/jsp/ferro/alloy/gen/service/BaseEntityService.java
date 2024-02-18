package com.jsp.ferro.alloy.gen.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.jsp.ferro.alloy.gen.entity.BaseAdditionalFields;
import com.jsp.ferro.alloy.gen.entity.BaseEntity;
import com.jsp.ferro.alloy.security.AuthenticationService;
import com.jsp.ferro.alloy.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

/**
 * @author Sajan
 *
 */
@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity,D extends JpaRepository<E, Long> > {
	 private final D dao;
	 private static final Integer DEFAULT_PAGE = 0;
	 private static final Integer DEFAULT_SIZE = 10;
	 private  AuthenticationService authenticationService;
	 @Autowired
	    public void setJwtTokenProvider(@Lazy AuthenticationService authenticationService) {
	        this.authenticationService = authenticationService;
	    }
	 public D getDao() {
	        return dao;
	    }
	 
	 public List<E> findAll(){
		 return dao.findAll();
	    }

	    public Optional<E> findById(Long id){
	        return dao.findById(id);
	    }

	    public E save(E entity){
	        setAdditionalFields(entity);
	        entity = dao.save(entity);
	        return entity;
	    }
	    
	    private void setAdditionalFields(E entity) {
	        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

	        if (baseAdditionalFields == null){
	            baseAdditionalFields = new BaseAdditionalFields();
	            entity.setBaseAdditionalFields(baseAdditionalFields);
	        }

	        if (entity.getId() == null){
	            baseAdditionalFields.setCreatedDate(new Date());
	            baseAdditionalFields.setCreatedBy(getCurrentCustomerId());
	        }else {
	        	baseAdditionalFields.setUpdatedDate(new Date());
	        	baseAdditionalFields.setUpdatedBy(getCurrentCustomerId());
	        }
	        
	        
	    }
	    public Integer getCurrentCustomerId() {
	        Integer currentCustomerId = authenticationService.getCurrentCustomerId();
	        return currentCustomerId;
	    }
}
