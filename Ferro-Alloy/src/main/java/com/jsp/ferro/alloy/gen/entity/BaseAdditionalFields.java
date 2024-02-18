package com.jsp.ferro.alloy.gen.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {
	@Column(name="created_date",updatable = false)
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "deleted",columnDefinition = "tinyint(1) default 0")
	private boolean deleted;
	
}
