package com.jsp.ferro.alloy.qlty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.ferro.alloy.qlty.entity.ChemicalCompositionHistory;

/**
 * @author Sajan
 *
 */
@Repository
public interface ChemicalCompositionHistoryDao extends JpaRepository<ChemicalCompositionHistory, Long> {

}
