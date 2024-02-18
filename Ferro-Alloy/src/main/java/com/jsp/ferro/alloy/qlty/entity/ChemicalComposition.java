package com.jsp.ferro.alloy.qlty.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chemical_composition")
public class ChemicalComposition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "material")
	private String material;
	
	@Column(name = "min")
	private String min;
	
	@Column(name = "max")
	private String max;

	@Column(name = "item_code")
	private String itemCode;
	
	@Column(name = "actual")
	private String actual;
	
	@Column(name = "c_eq")
	private String ceq;
	
	@Column(name = "lean_chemistry_grades",columnDefinition = "TEXT")
	private String leanChemGrade;
	
	@ManyToOne
	@JsonBackReference(value="chemicalComposition")
	@JoinColumn(name = "fk_id")
	private QualityRequest fkId;
}
