package com.jsp.ferro.alloy.qlty.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ferro.alloy.exception.CustomException;
import com.jsp.ferro.alloy.qlty.dto.QualityRequestDto;
import com.jsp.ferro.alloy.qlty.entity.QualityRequest;
import com.jsp.ferro.alloy.qlty.service.QualityService;
import com.jsp.ferro.alloy.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

/**
 * @author Sajan
 * @Discription all the action perform are related to quality
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class QualityController {

	private final QualityService qualityService;

	@Operation(tags = "QualityController", summary = "Get all the list of quality request. ", description = "Get list of quality request.")
	@GetMapping("/v1/products")
	public Page<QualityRequestDto> fatchAllRequest(@RequestParam Optional<Integer> page,
			@RequestParam Optional<String> sortBy, @RequestParam Optional<String> product,
			@RequestParam Optional<String> grade, @RequestParam Optional<String> internalGrade) {
		return qualityService.fatchAllRequest(page, sortBy, product, grade, internalGrade);

	}

	@Operation(tags = "QualityController", summary = "Get quality request by id. ", description = "Get quality request by id.")
	@GetMapping("/v1/products/{id}")
	public ResponseEntity<QualityRequestDto> fatchRequestById(@PathVariable Optional<Long> id) {
		QualityRequestDto qualityRequest = qualityService
				.fatchRequestId(id.orElseThrow(() -> new CustomException("Id can not be null", HttpStatus.NOT_FOUND)));
		return ResponseEntity.ok(qualityRequest);
	}

	@Operation(tags = "QualityController", summary = "Post product request. ", description = "Save product")
	@PostMapping("/v1/save-product")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<RestResponse<QualityRequestDto>> saveProductRequest(
			@RequestBody QualityRequestDto qualityDto) {
		QualityRequestDto qualityRequestDto = qualityService.saveProductRequest(qualityDto);
		return ResponseEntity.ok(RestResponse.of(qualityRequestDto));
	}

	@Operation(tags = "QualityController", summary = "Find by external grade ", description = "find by external grade")
	@GetMapping("/v1/find-products/{grade}")
	public Page<QualityRequestDto> findProduct(@PathVariable Optional<String> grade) {
		return qualityService.findProduct(grade);
	}

}
