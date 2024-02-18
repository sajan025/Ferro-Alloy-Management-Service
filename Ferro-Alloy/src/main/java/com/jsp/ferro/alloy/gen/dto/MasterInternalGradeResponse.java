package com.jsp.ferro.alloy.gen.dto;

import java.util.List;


import com.jsp.ferro.alloy.gen.entity.MasterInternalGrade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterInternalGradeResponse {
	private boolean success;
    private List<MasterInternalGrade> internalGradeList;
}
