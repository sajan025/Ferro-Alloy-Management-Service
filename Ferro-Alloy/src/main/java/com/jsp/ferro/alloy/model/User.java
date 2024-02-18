package com.jsp.ferro.alloy.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Sajan Yadav
 * @Date 12 Sept 2023
 */
@Entity
@Getter
@Setter
@Table(name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_pk_id")
	private Integer userPkId;
	
	@Column(name = "username", nullable=false, unique=true)
	private String userName;
	
	@Column(name = "password", nullable = false, updatable= true)
	private String password;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "sap_id")
	private String sapId;
	
	
	@Column(name = "loction_fk_id")
	private Integer loctionFkId;
	
	
	@Column(name = "company_fk_id")
	private Integer companyFkId;
	
	
	@Column(name = "department_fk_id")
	private Integer departmentFkId;
	
	@Column(name = "designation_fk_id")
	private Integer designationFkId;
	
	@Column(name = "created_by")
	private User createdBy;

	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_by")
	private User updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
		
	@Column(name = "deleted",columnDefinition = "tinyint(1) default 0")
	private boolean deleted;

	@JsonManagedReference(value="role")
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "user_fk_id", referencedColumnName = "user_pk_id")
	private List<UserRole> userRole;


	/*
	 * @Transient public JSONObject toJSON() { JSONObject json = new JSONObject();
	 * try { json.accumulate("userPkId", userPkId); json.accumulate("email", email);
	 * json.accumulate("name", name); } catch (JSONException e) {
	 * e.printStackTrace(); } return json; }
	 */
	

}
