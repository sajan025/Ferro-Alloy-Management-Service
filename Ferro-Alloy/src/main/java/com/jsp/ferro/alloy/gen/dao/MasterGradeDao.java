package com.jsp.ferro.alloy.gen.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.jsp.ferro.alloy.gen.entity.MasterGrade;

@Repository
public interface MasterGradeDao extends JpaRepository<MasterGrade, Integer> {

	@Query("From MasterGrade m where m.gradeFamily like %:key%  order by m.gradeFamily")
	List<MasterGrade> fetchMasterGrade(@Param("key")String key,Pageable pageable);
	

}
