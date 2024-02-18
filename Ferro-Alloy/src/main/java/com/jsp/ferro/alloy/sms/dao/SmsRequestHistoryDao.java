package com.jsp.ferro.alloy.sms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.ferro.alloy.sms.entity.SMsRequestHistrory;

@Repository
public interface SmsRequestHistoryDao extends JpaRepository<SMsRequestHistrory, Long> {
	@Query("SELECT q FROM SMsRequestHistrory q  where q.qualityRequestFkId=:qualityFkId ORDER BY q.id DESC")
	List<SMsRequestHistrory> findAllByQualityFkId(@Param("qualityFkId") Optional<Integer> qualityFkid);

}

