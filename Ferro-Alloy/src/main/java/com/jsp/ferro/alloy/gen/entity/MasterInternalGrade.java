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

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "internal_master_grade")
public class MasterInternalGrade implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "int_grade_pk_id")
	private Integer intGradePkId;

	@Column(name = "int_grade_Name")
	private String intGradeName;
	
	@Column(name = "int_grade_code")
	private String intGradeCode;
	
	@Column(name = "int_grade_desc")
	private String intGradeDesc;
	
	@Column(name = "int_min_Range")
	private String minRange;
	
	@Column(name = "int_max_Range")
	private String maxRange;
	
	
	@Column(name = "operator_min")
	private String operatorMin;
	
	@Column(name = "operator_max")
	private String operatorMax;
	
	
	@Column(name = "grade_fk_id")
	private Integer gradeFkId;
	
	@Column(name = "location_fk_id")
	private Integer locationFkId;

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
	
	@Column(name = "by_supply_condation")
	private String supplyCondation;
	
	@Column(name = "sap_code",length=4)
	private String sapCode;
	
	@Column(name="sap_send_flag", columnDefinition = "tinyint(1) default 0")
	private boolean sapSendFlag;
	
	@Transient
	public JSONObject toJSON() {
	JSONObject json = new JSONObject();
	try {
	json.accumulate("intGradePkId", intGradePkId);
	json.accumulate("intGradeName", intGradeName);
	
	} catch (JSONException e) {
	e.printStackTrace();
	}
	return json;
	}
}
