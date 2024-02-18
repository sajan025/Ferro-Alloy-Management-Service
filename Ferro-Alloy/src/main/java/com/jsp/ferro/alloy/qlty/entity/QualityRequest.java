package com.jsp.ferro.alloy.qlty.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jsp.ferro.alloy.gen.entity.BaseEntity;

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
@Table(name = "quality_request")
public class QualityRequest extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3000558973194566820L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "product")
	private String product;
	
	@Column(name = "external_grade")
	private String externalGrade;

	@Column(name = "internal_grade")
	private String internalGrade;
	
	
	@Column(name = "remarks",columnDefinition = "TEXT")
	private String remarks;
	
	@Column(name = "status")
	private String status;
	
	@JsonManagedReference(value="chemicalComposition")
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ChemicalComposition.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id", referencedColumnName = "id")
	private List<ChemicalComposition> compositionList;

}
