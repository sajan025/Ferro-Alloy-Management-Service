package com.jsp.ferro.alloy.finance.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ferro.alloy.finance.dto.FinanceCostingDto;
import com.jsp.ferro.alloy.finance.dto.FinanceHistoryDto;
import com.jsp.ferro.alloy.finance.dto.FinanceRequestDto;
import com.jsp.ferro.alloy.finance.entity.FerroCosting;
import com.jsp.ferro.alloy.finance.entity.FerroCostingHistory;
import com.jsp.ferro.alloy.finance.entity.FinanceRequest;
import com.jsp.ferro.alloy.finance.entity.FinanceRequestHistrory;
import com.jsp.ferro.alloy.finance.service.FinanceService;
import com.jsp.ferro.alloy.qlty.dto.QualityRequestDto;
import com.jsp.ferro.alloy.qlty.service.QualityService;
import com.jsp.ferro.alloy.response.RestResponse;
import com.jsp.ferro.alloy.sms.entity.SMsRequestHistrory;
import com.jsp.ferro.alloy.sms.service.SmsService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("financeapi")
public class FinanceController {
	private final FinanceService financeService;
	private final SmsService smsService;

	@Operation(tags = "FinanceController", summary = "Post finance request. ", description = "Save Finance")
	@PostMapping("/saveFinance")
	public ResponseEntity<RestResponse<FinanceRequestDto>> saveFinanceRequest(
			@RequestBody FinanceRequestDto financeDto) {
		FinanceRequestDto financeRequestDto = financeService.saveFinanceRequest(financeDto);
		return ResponseEntity.ok(RestResponse.of(financeRequestDto));
	}

	@Operation(tags = "FinanceController", summary = "Post Cost request. ", description = "Save Cost")

	@PostMapping("/saveCost")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Map<String,String>> saveCostRequest(@RequestBody List<FerroCosting> ferroCost) {
		Map<String,String> body=new HashedMap<String, String>();
		financeService.saveFinanceCostRequest(ferroCost);
		body.put("message", "Saved successfully");
		return ResponseEntity.ok(body);
	}

	@Operation(tags = "FinanceController", summary = "Get all the list of quality request. ", description = "Get list of quality request.")
	@GetMapping("/v1/getQualityList")
	public Page<QualityRequestDto> fatchAllRequest(@RequestParam Optional<Integer> page,
			@RequestParam Optional<String> sortBy, @RequestParam Optional<String> product,
			@RequestParam Optional<String> grade, @RequestParam Optional<String> internalGrade) {
		return smsService.fatchAllRequest(page, sortBy, product, grade, internalGrade);
	}

	@Operation(tags = "FinanceController", summary = "Get all the list of Finance request. ", description = "Get list of finance Data request.")
	@GetMapping("/v1/financeData")
	public FinanceRequest financeData(@RequestParam Optional<Integer> id) {
		return financeService.fatchAllRequest1(id);
	}
	
	
	@Operation(tags = "FinanceController", summary = "Get all the list of Finance request. ", description = "Get list of finance Data request.")
	@GetMapping("/v1/costing-rates")
	public ResponseEntity<RestResponse<List<FerroCosting>>> getCostingRate(@RequestParam Optional<Integer> id) {
		List<FerroCosting> costingList=financeService.fatchAllCostingRate(id);
		return ResponseEntity.ok(RestResponse.of(costingList));

	}

	@Operation(tags = "FinanceController", summary = "Get all the list of Finance History request. ", description = "Get list of finance History Data request.")
	@GetMapping("/v1/financeHistoryData")
	public List<FinanceRequestHistrory> financeHistoryData(@RequestParam Optional<Integer> id) {
		return financeService.fatchfinanceHistoryData(id);
	}
	
	@Operation(tags = "FinanceController", summary = "Get all the list of costing History request. ", description = "Get list of costing History Data request.")
	@GetMapping("/v1/costingHistoryData")
	public List<FerroCostingHistory> costingHistoryData(@RequestParam Optional<Integer> id) {
		return financeService.fatchCostingHistoryData(id);
	}
}
