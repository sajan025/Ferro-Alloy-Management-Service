package com.jsp.ferro.alloy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sajan
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="common_constant")
public class CommonConstant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_id")
	private Long id;
	
	
	@Column(name = "constant_code")
	private String constantCode;
	
	@Column(name = "constant_type")
	private String constantType;
	
	@Column(name = "constant_name")
	private String constantName;
	
	@Column(name = "sequence_number")
	private Integer sequenceNumber;
	
	@Column(name="deleted", columnDefinition = "tinyint(1) default 0")
	private boolean deleted;
}
