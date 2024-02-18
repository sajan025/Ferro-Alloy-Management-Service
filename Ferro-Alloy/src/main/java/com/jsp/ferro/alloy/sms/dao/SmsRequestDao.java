package com.jsp.ferro.alloy.sms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.ferro.alloy.sms.entity.SmsRequest;

@Repository
public interface SmsRequestDao extends JpaRepository<SmsRequest, Long> {
	@Query("SELECT q FROM SmsRequest q  where q.qualityRequestFkId=:qualityFkId")
	SmsRequest findByQualityRequestFkId(@Param("qualityFkId") Integer qualityFkId);

}
