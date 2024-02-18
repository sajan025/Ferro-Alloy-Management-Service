package com.jsp.ferro.alloy.finance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.ferro.alloy.finance.entity.FerroCosting;

@Repository
public interface FerrorCostingDao extends JpaRepository<FerroCosting, Long> {

	@Query("SELECT q FROM FerroCosting q  where q.qualityRequestFkId=:qualityFkId")
	List<FerroCosting> fatchAllCostingRate(@Param("qualityFkId") Optional<Integer> qualityFkId);

}
