package com.jsp.ferro.alloy.finance.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.jsp.ferro.alloy.qlty.dto.ChemicalCompositionDto;
import com.jsp.ferro.alloy.qlty.dto.QualityRequestDto;

import lombok.Data;

@Data
public class FinanceRequestDto {

	private Long id;
	private String segment;
	private String subSegment;
    private String alloyExtra;
	private String qcRejectionImpactPer;
    private String qcRejectionImpactRs;
    private String smsProcessExtra;
	private String plateMillYieldImpact;
    private String plateMillNco;
	private String plateMillNcoImpact;
	private String plateMillRejection;
    private String plateMillRejectionImpact;
    private String processCharges;
    private String status;
    private String totalRevisedCost;
    private String remarks;
    private String product;
	private String externalGrade;
	private String internalGrade;
	private Integer ferroCostingRateFkId;
	private Integer qualityRequestFkId;

   

}
