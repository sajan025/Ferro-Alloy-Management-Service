package com.jsp.ferro.alloy.qlty.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.ferro.alloy.qlty.entity.QualityRequest;

/**
 * @author Sajan
 *
 */
@Repository
public interface QualityDao extends JpaRepository<QualityRequest, Long> {
	@Query("SELECT q FROM QualityRequest q WHERE ( q.product = :product) OR ( q.externalGrade= :grade) OR ( q.internalGrade= :internalGrade)")
	Page<QualityRequest> searchByParam(@Param("product") Optional<String> product,@Param("grade") Optional<String> grade,
			@Param("internalGrade") Optional<String> internalGrade,Pageable pageable);
	
	@Query("SELECT q FROM QualityRequest q WHERE ( q.product = :product) OR ( q.externalGrade= :grade) OR ( q.internalGrade= :internalGrade) AND q.status='Submit'")
	Page<QualityRequest> search(@Param("product") Optional<String> product,@Param("grade") Optional<String> grade,
			@Param("internalGrade") Optional<String> internalGrade,Pageable pageable);
	
	@Query(value="SELECT * FROM quality_request  WHERE ( external_grade= :grade)  order by created_date DESC",nativeQuery = true)
	Page<QualityRequest> findByExternalGrade(@Param("grade") Optional<String> grade,Pageable pageable);
	
	@Query("SELECT q FROM QualityRequest q WHERE q.status=:status")
	Page<QualityRequest> findAllByStatus(PageRequest of, @Param("status") String status);

	

}
