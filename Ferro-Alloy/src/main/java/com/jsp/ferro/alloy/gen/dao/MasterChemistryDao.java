package com.jsp.ferro.alloy.gen.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jsp.ferro.alloy.gen.entity.MasterChemistry;


@Repository
public interface MasterChemistryDao extends JpaRepository<MasterChemistry, Integer> {

	Optional<Page<MasterChemistry>> findByGrade(Optional<String> externalGrade,Pageable pageable);

}
