package com.jsp.ferro.alloy.finance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jsp.ferro.alloy.gen.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "finance_costing_histrory")
public class FerroCostingHistory extends BaseEntity{
	
	private static final long serialVersionUID = 3000558973194566820L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "quality_request_fk_id")
	private Integer qualityRequestFkId;
	@Column(name = "costing_rate_date")
	private String costingRateDate;
	@Column(name = "item_code")
	private String itemCode;
    @Column(name = "description")
    private String description;
	@Column(name = "base_rates")
	private String baseRates;
	@Column(name = "rates_lcnc_basis")
	private String ratesLcncBasis;
	@Column(name = "sap_rate")
	private String sapRate;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "ferro_costing_fk_id")
	private Long ferroCostingFkId;

}
