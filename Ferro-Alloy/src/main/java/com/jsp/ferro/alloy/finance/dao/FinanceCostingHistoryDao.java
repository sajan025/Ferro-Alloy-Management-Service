package com.jsp.ferro.alloy.finance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.ferro.alloy.finance.entity.FerroCostingHistory;

@Repository
public interface FinanceCostingHistoryDao extends JpaRepository<FerroCostingHistory, Long>{
	@Query("SELECT q FROM FerroCostingHistory q  where q.qualityRequestFkId=:id")
	List<FerroCostingHistory> fatchCostingHistoryData(@Param("id") Optional<Integer> id);

}
