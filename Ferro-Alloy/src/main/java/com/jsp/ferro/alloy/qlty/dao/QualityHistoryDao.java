package com.jsp.ferro.alloy.qlty.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.ferro.alloy.qlty.entity.QualityRequestHistory;

/**
 * @author Sajan
 *
 */
@Repository
public interface QualityHistoryDao extends JpaRepository<QualityRequestHistory, Long> {
	
	@Query("SELECT q FROM QualityRequestHistory q  where q.fkId=:id")
	List<QualityRequestHistory> fatchQualityHistoryData(@Param("id")Optional<Integer> id);

}