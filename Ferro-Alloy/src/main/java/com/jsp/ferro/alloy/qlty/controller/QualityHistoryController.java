package com.jsp.ferro.alloy.qlty.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ferro.alloy.qlty.entity.QualityRequestHistory;
import com.jsp.ferro.alloy.qlty.service.QualityHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

/**
 * @author Sajan
 * @Discription all the action perform are related to quality history
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class QualityHistoryController {

	private final QualityHistoryService qualityHisoryService;

	@Operation(tags = "Quality History Controller", summary = "Get all the list of quality History request. ", description = "Get list ofquality History Data request.")
	@GetMapping("/v1/qualityHistoryData")
	public List<QualityRequestHistory> qualityHistoryData(@RequestParam Optional<Integer> id) {
		return qualityHisoryService.fatchQualityHistoryData(id);
	}
}
