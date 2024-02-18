package com.jsp.ferro.alloy.qlty.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jsp.ferro.alloy.qlty.entity.ChemicalComposition;

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
public class QualityRequestDto {
	private Long id;
	private String product;
	private String externalGrade;
	private String internalGrade;
	private String remarks;
	private String status;
	private List<ChemicalCompositionDto> compositionList;
}
