package com.jsp.ferro.alloy.gen.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sajan
 *
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements BaseModel,Serializable,Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3014572400942714014L;
	@Embedded
	BaseAdditionalFields baseAdditionalFields;
}
