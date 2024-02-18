package com.jsp.ferro.alloy.finance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.ferro.alloy.finance.entity.FinanceRequestHistrory;
@Repository
public interface FinanceHistoryDao extends JpaRepository<FinanceRequestHistrory, Long> {
	@Query("SELECT q FROM FinanceRequestHistrory q  where q.qualityRequestFkId=:id")
	List<FinanceRequestHistrory> fatchfinanceHistoryData(@Param("id")Optional<Integer> id);

}
