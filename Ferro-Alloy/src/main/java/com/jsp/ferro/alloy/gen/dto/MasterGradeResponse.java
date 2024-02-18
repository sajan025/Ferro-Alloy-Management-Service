package com.jsp.ferro.alloy.gen.dto;

import java.util.List;

import com.jsp.ferro.alloy.gen.entity.MasterGrade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterGradeResponse {
	private boolean success;
    private List<MasterGrade> masterGradeList;
	
}
