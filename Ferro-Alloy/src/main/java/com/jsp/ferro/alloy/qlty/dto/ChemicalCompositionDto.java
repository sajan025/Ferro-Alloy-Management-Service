package com.jsp.ferro.alloy.qlty.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jsp.ferro.alloy.qlty.entity.QualityRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sajan
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChemicalCompositionDto {
	private Long id;
	private String material;
	private String min;
	private String max;
	private String itemCode;
	private String actual;
	private String ceq;
	private String leanChemGrade;
	
}
