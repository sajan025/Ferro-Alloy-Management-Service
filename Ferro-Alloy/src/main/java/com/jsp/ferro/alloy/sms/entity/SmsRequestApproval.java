package com.jsp.ferro.alloy.sms.entity;

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
@Table(name = "sms_request_approval")
public class SmsRequestApproval  extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	 @Column(name = "approval_document")
	 private String approvalDocument;
	 @Column(name = "approve_time")
	 private String approveTime;
	 @Column(name = "approver_comments")
	 private String approverComments;
	 @Column(name = "approver_level")
	 private String approverLevel;
	 @Column(name = "pending_date")
	 private String pendingDate;
	 @Column(name = "pending_with_approver")
	 private String pendingWithApprover;
	 @Column(name = "status")
	 private String status;
	 @Column(name = "approver_id")
	 private String approverId;
	 @Column(name = "quality_request_fk_id")
	 private Integer qualityRequestFkId;
}
