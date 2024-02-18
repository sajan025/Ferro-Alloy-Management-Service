package com.jsp.ferro.alloy.gen.dto;

import java.util.List;

import com.jsp.ferro.alloy.gen.entity.MasterChemistry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterChemistryResponse {
	private boolean success;
    private List<MasterChemistry> chemistryList;

}
