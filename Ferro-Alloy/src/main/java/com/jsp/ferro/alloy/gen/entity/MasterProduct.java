package com.jsp.ferro.alloy.gen.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


	

@Entity
@Getter
@Setter
@Table(name = "master_product")
public class  MasterProduct implements Serializable{
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "grade_pk_id")
	private Long productPkId;

	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_code")
	private String productCode;
	
	
	@Column(name="deleted", columnDefinition = "tinyint(1) default 0")
	private boolean deleted;


}
