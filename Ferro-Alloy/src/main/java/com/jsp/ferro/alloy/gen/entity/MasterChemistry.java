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
@Table(name = "master_ladle_chemistry")
public class MasterChemistry implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_id")
	private Long pkId;
	
	@Column(name = "grade_fk_id")
	private Integer gradeFkId;
	
	@Column(name = "internal_grade_fk_id")
	private Integer internalGradeFkId;
	
	@Column(name = "grade")
	private String grade;
	
	@Column(name = "internal_grade")
	private String internalGrade;
	
	@Column(name = "c_min")
	private String cMin;
	
	@Column(name = "c_max")
	private String cMax;
	
	@Column(name = "mn_min")
	private String mnMin;
	
	@Column(name = "mn_max")
	private String mnMax;
	
	@Column(name = "si_min")
	private String siMin;
	
	@Column(name = "si_max")
	private String siMax;
	
	@Column(name = "cr_min")
	private String crMin;
	
	@Column(name = "cr_max")
	private String crMax;
	
	@Column(name = "v_min")
	private String vMin;
	
	@Column(name = "v_max")
	private String vMax;
	
	@Column(name = "cu_min")
	private String cuMin;
	
	@Column(name = "cu_max")
	private String cuMax;
	
	@Column(name = "ni_min")
	private String niMin;
	
	@Column(name = "ni_max")
	private String niMax;
	
	@Column(name = "nb_min")
	private String nbMin;
	
	@Column(name = "nb_max")
	private String nbMax;
	
	@Column(name = "mo_max")
	private String moMax;
	
	@Column(name = "mo_min")
	private String moMin;
	

	@Column(name = "b_max")
	private String bMax;
	
	@Column(name = "b_min")
	private String bMin;
	
	@Column(name = "p_min")
	private String pMin;
	
	@Column(name = "p_max")
	private String pMax;
	
	@Column(name = "al_min")
	private String alMin;
	
	@Column(name = "al_max")
	private String alMax;
	
    @Column(name = "ti_min")
	private String tiMin;
	
	@Column(name = "ti_max")
	private String tiMax;
	
	@Column(name = "n_min",columnDefinition = "decimal(7,3) default 0")
	private String nMin;
	
	@Column(name = "n_max",columnDefinition = "decimal(7,3) default 0")
	private String nMax;
	
	@Column(name = "ca_min")
	private String caMin;
	
	@Column(name = "ca_max")
	private String caMax;
	
	@Column(name = "deleted",columnDefinition = "tinyint(1) default 0")
	private Boolean deleted;

}
