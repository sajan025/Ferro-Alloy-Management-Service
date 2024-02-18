package com.jsp.ferro.alloy.sms.controller;

import java.util.List;
import java.util.Optional;

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

import com.jsp.ferro.alloy.qlty.dto.QualityRequestDto;
import com.jsp.ferro.alloy.response.RestResponse;
import com.jsp.ferro.alloy.sms.dto.SmsRequestDto;
import com.jsp.ferro.alloy.sms.entity.SMsRequestHistrory;
import com.jsp.ferro.alloy.sms.service.SmsService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class SmsController {
	private final SmsService smsService;
	
	@Operation(tags = "SmsController", summary = "Fatch all the sms request history data ", description = "Get list of sms history request.")
	@GetMapping("/v1/sms-history-data")
	public ResponseEntity<RestResponse<List<SMsRequestHistrory>>> SmsHistoryData(@RequestParam Optional<Integer> qualityFkid) {
		return ResponseEntity.ok(RestResponse.of(smsService.fatchSmsHistoryData(qualityFkid)));

	}
	
	@Operation(tags = "SmsController", summary = "Fatch all the sms request data ", description = "Get list of sms request.")
	@GetMapping("/v1/sms-data")
	public ResponseEntity<RestResponse<SmsRequestDto>> fatchAllSmsData(@RequestParam Optional<Integer> qualityFkid) {
		return ResponseEntity.ok(RestResponse.of(smsService.fatchAllSmsData(qualityFkid)));

	}
	
	@Operation(tags = "SmsController", summary = "Save Sms Data ", description = "save sms data")
	@PostMapping("/v1/save-sms-data")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<RestResponse<SmsRequestDto>> saveSmsData(
			@RequestBody SmsRequestDto smsRequestDto) {
		SmsRequestDto smsRequest = smsService.saveSmsData(smsRequestDto);
		return ResponseEntity.ok(RestResponse.of(smsRequest));
	}
	
	@Operation(tags = "SmsController", summary = "Get all the list of quality request. ", description = "Get list of quality request.")
	@GetMapping("/v1/products-list")
	public Page<QualityRequestDto> fatchAllRequest(@RequestParam Optional<Integer> page,
			@RequestParam Optional<String> sortBy, @RequestParam Optional<String> product,
			@RequestParam Optional<String> grade, @RequestParam Optional<String> internalGrade) {
		return smsService.fatchAllRequest(page, sortBy, product, grade, internalGrade);

	}

}
