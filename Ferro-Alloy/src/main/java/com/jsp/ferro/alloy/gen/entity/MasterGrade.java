package com.jsp.ferro.alloy.gen.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Setter;

import lombok.Getter;

@Entity
@Getter
@Setter
@Table(name = "master_grade")
public class MasterGrade implements Serializable{
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "grade_pk_id")
	private Long gradePkId;

	@Column(name = "grade_family")
	private String gradeFamily;
	
	@Column(name = "grade_code")
	private String gradeCode;
	
	@Column(name = "grade_desc")
	private String gradeDesc;
	
	@Column(name = "location_fk_id")
	private Integer locationFkId;

	
	@Column(name = "plant_fk_id")
	private Integer plantFkId; 
	

	@Column(name = "sbu_fk_id")
	private Integer sbuFkId;
	
	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name="deleted", columnDefinition = "tinyint(1) default 0")
	private boolean deleted;

	@Column(name="auto_tdc", columnDefinition = "tinyint(1) default 0")
	private boolean autoTdc;
	
	@Column(name = "sap_code",length=4)
	private String sapCode;
	
	@Transient
	public JSONObject toJSON() {
	JSONObject json = new JSONObject();
	try {
	json.accumulate("gradePkId", gradePkId);
	json.accumulate("gradeFamily", gradeFamily);
	
	} catch (JSONException e) {
	e.printStackTrace();
	}
	return json;
	}
	
	
}