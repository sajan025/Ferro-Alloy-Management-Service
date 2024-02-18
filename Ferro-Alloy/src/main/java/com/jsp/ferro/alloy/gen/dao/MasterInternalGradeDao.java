package com.jsp.ferro.alloy.gen.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.jsp.ferro.alloy.gen.entity.MasterInternalGrade;


@Repository
public interface MasterInternalGradeDao extends JpaRepository<MasterInternalGrade, Integer>  {
	@Query("From MasterInternalGrade m where m.intGradeName like %:key%  order by m.intGradeName")
	List<MasterInternalGrade> fetchMasterInternalGrade(@Param("key") String key, PageRequest pageRequest);

}
