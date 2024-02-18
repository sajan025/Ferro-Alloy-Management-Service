package com.jsp.ferro.alloy.finance.dto;



import lombok.Data;

@Data
public class FinanceCostingDto {
	private Long id;
    private String qualityRequestFkId;
	private String costingRateDate;
	private String itemCode;
    private String description;
	private String baseRates;
	private String ratesLcncBasis;
    private String sapRate;
	
	private String status;

}
