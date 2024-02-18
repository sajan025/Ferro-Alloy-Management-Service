package com.jsp.ferro.alloy.gen.entity;

import java.util.Date;

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
@Table(name = "ferro_alloy_specification")
public class MasterFerroAlloySpecification {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "material_code")
	private String materialCode;
	
	@Column(name = "material")
	private String material;
	
	@Column(name = "C")
	private String C;
	
	@Column(name = "Mn")
	private String Mn;
	
	@Column(name = "S")
	private String S;
	
	@Column(name = "P")
	private String P;
	
	@Column(name = "Si")
	private String Si;
	
	@Column(name = "Al")
	private String Al;
	
	@Column(name = "Cu")
	private String Cu;
	
	@Column(name = "Ca")
	private String Ca;
	
	@Column(name = "Ni")
	private String Ni;
	
	@Column(name = "Cr")
	private String Cr;
	
	@Column(name = "Mo")
	private String Mo;
	
	@Column(name = "V")
	private String V;
	
	@Column(name = "Sn")
	private String Sn;
	
	@Column(name = "_AS")
	private String _AS;
	
	@Column(name = "Ti")
	private String Ti;
	
	@Column(name = "Nb")
	private String Nb;
	
	@Column(name = "Pb")
	private String Pb;
	
	@Column(name = "B")
	private String B;
	
	@Column(name = "N")
	private String N;
	
	

}
