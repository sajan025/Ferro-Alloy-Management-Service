package com.jsp.ferro.alloy.finance.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jsp.ferro.alloy.gen.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "finance_request")
public class FinanceRequest extends BaseEntity{
	
	private static final long serialVersionUID = 3000558973194566820L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "segment")
	private String segment;
	
	@Column(name = "Sub_segment")
	private String subSegment;
	
	@Column(name = "alloy_extra")
	private String alloyExtra;
	
	@Column(name = "qc_rejection_impact_per")
	private String qcRejectionImpactPer;
	
	@Column(name = "qc_rejection_Impact_rs")
	private String qcRejectionImpactRs;
	
	@Column(name = "sms_process_extra")
	private String smsProcessExtra;
	
	@Column(name = "plate_mill_yield_impact")
	private String plateMillYieldImpact;
	
	@Column(name = "plate_mill_nco")
	private String plateMillNco;
	
	@Column(name = "plate_mill_nco_impact")
	private String plateMillNcoImpact;
	
	@Column(name = "plate_mill_rejection")
	private String plateMillRejection;
	
	@Column(name = "plate_mill_rejection_impact")
	private String plateMillRejectionImpact;

	@Column(name = "Process_Charges_other_than_Base_Grade")
	private String processCharges;
	
	@Column(name = "total_revised_cost")
	private String totalRevisedCost;

	@Column(name = "remarks",columnDefinition = "TEXT")
	private String remarks;
	
	@Column(name = "ferro_costing_rate_fk_id")
	private Integer ferroCostingRateFkId;
	
	@Column(name = "quality_request_fk_id")
	private Integer qualityRequestFkId;
	
    @Column(name = "status")
	private String status;
	
}
