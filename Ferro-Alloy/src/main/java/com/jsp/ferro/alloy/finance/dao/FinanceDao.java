package com.jsp.ferro.alloy.finance.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.ferro.alloy.finance.dto.FinanceRequestDto;
import com.jsp.ferro.alloy.finance.entity.FinanceRequest;
import com.jsp.ferro.alloy.qlty.entity.QualityRequest;


@Repository
public interface FinanceDao extends JpaRepository<FinanceRequest, Long> {

	
	@Query("SELECT f FROM FinanceRequest f LEFT JOIN  QualityRequest q ON f.qualityRequestFkId = q.id WHERE ( q.product = :product) OR ( q.externalGrade= :grade) OR ( q.internalGrade= :internalGrade)")
	Page<FinanceRequest> searchByParam(@Param("product") Optional<String> product,@Param("grade") Optional<String> grade,
			@Param("internalGrade") Optional<String> internalGrade,Pageable pageable);
	
	@Query("SELECT f FROM FinanceRequest f where f.qualityRequestFkId = :id ")
	FinanceRequest getById(Optional<Integer> id);

}
