package com.jsp.ferro.alloy.gen.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.ferro.alloy.exception.CustomException;
import com.jsp.ferro.alloy.gen.dao.FerroAlloySpecificationDao;
import com.jsp.ferro.alloy.gen.dao.MasterChemistryDao;
import com.jsp.ferro.alloy.gen.dao.MasterGradeDao;
import com.jsp.ferro.alloy.gen.dao.MasterInternalGradeDao;
import com.jsp.ferro.alloy.gen.dto.MasterChemistryResponse;
import com.jsp.ferro.alloy.gen.dto.MasterGradeResponse;
import com.jsp.ferro.alloy.gen.dto.MasterInternalGradeResponse;
import com.jsp.ferro.alloy.gen.entity.MasterChemistry;
import com.jsp.ferro.alloy.gen.entity.MasterFerroAlloySpecification;
import com.jsp.ferro.alloy.gen.entity.MasterGrade;
import com.jsp.ferro.alloy.gen.entity.MasterInternalGrade;
import com.jsp.ferro.alloy.gen.service.EmailService;
import com.jsp.ferro.alloy.qlty.dto.QualityRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Sajan Yadav
 * @Date 30-10-2023
 */
@Tag(name = "RestapiCallController", description = "For Rest Call to Get Master Data")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api")
public class RestapiCallController {
	static ResourceBundle bundle = ResourceBundle.getBundle("application");
	@Autowired
	MasterGradeDao dao;
	@Autowired
	MasterInternalGradeDao idao;
	@Autowired
	MasterChemistryDao cdao;
	@Autowired
	FerroAlloySpecificationDao ferroDao;
//	@Autowired
//	EmailService mailService;

	@Operation(description = "fatch all master grade data.")
	@GetMapping("/master-grades")
	public void saveMasterGradeData() {
		String url = bundle.getString("tdc.master.grade.url");
		RestTemplate restTemplate = new RestTemplate();
		try {
			System.out.println("start time:::: " + LocalDateTime.now());
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
			String resBody = responseEntity.getBody();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			MasterGradeResponse gradeResponse = objectMapper.readValue(resBody, MasterGradeResponse.class);
			if (gradeResponse.isSuccess()) {
				// Declarative style using java8 stream api -sajan
				gradeResponse.getMasterGradeList().parallelStream().forEach(grade -> {
					if (grade != null)
						saveEntity(grade);
				});
				System.out.println("Successfully synced.");
				System.out.println("End time:::: " + LocalDateTime.now());
			} else {
				throw new RuntimeException("Somthing went wrong on Tdc REST api, Please check and try again.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Somting went wrong.");
		}

	}

	private synchronized void saveEntity(MasterGrade grade) {
		grade.setGradePkId(null);
		dao.save(grade);
	}

	@Operation(description = "fatch all master  internal grade data.")
	@GetMapping("/master-Internal-grades")
	public void saveMasterInternalGradeData() {
		String url = bundle.getString("tdc.master.Internalgrade.url");
		RestTemplate restTemplate = new RestTemplate();
		try {
			System.out.println("start time:::: " + LocalDateTime.now());
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
			String resBody = responseEntity.getBody();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			MasterInternalGradeResponse InternalgradeResponse = objectMapper.readValue(resBody,
					MasterInternalGradeResponse.class);
			if (InternalgradeResponse.isSuccess()) {
				// Declarative style using java8 stream api -sajan
				InternalgradeResponse.getInternalGradeList().parallelStream().forEach(grade -> {
					if (grade != null)
						saveInternalEntity(grade);
				});
				System.out.println("Successfully synced.");
				System.out.println("End time:::: " + LocalDateTime.now());
			} else {
				throw new RuntimeException("Somthing went wrong on Tdc REST api, Please check and try again.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Somting went wrong.");
		}

	}

	private synchronized void saveInternalEntity(MasterInternalGrade grade) {

		grade.setIntGradePkId(null);
		idao.save(grade);
	}

	@Operation(description = "fatch all master  Chemistry data.")
	@GetMapping("/master-Chemistry")
	public void saveMasterChemistryData() {
		String url = bundle.getString("tdc.master.Chemistry.url");
		RestTemplate restTemplate = new RestTemplate();
		try {
			System.out.println("start time:::: " + LocalDateTime.now());
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
			String resBody = responseEntity.getBody();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			MasterChemistryResponse ChemistrygradeResponse = objectMapper.readValue(resBody,
					MasterChemistryResponse.class);

			if (ChemistrygradeResponse.isSuccess()) {
				// Declarative style using java8 stream api -sajan
				ChemistrygradeResponse.getChemistryList().parallelStream().forEach(grade -> {
					// if (grade != null)
					// saveChemistryEntity(grade);
				});
				System.out.println("Successfully synced.");
				System.out.println("End time:::: " + LocalDateTime.now());
			} else {
				throw new RuntimeException("Somthing went wrong on Tdc REST api, Please check and try again.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Somting went wrong.");
		}

	}

//	private synchronized void saveChemistryEntity(MasterChemistry grade) {
//
//		grade.setMasterLadleChemistryPkId(null);
//		cdao.save(grade);
//	}

	@Operation(tags = "RestapiCallController", summary = "Search grade in master grade list. ", description = "search by grade family")
	@GetMapping("/v1/master-grade/{key}")
	public ResponseEntity<String> fetchMasterGrade(@PathVariable Optional<String> key) {
		List<MasterGrade> gradeList = dao.fetchMasterGrade(
				key.orElseThrow(() -> new CustomException("key can not be null", HttpStatus.NOT_FOUND)),
				PageRequest.of(0, 8));
		JSONArray array = new JSONArray();
		for (MasterGrade g : gradeList) {
			array.put(g.toJSON());
		}
		return ResponseEntity.ok(array.toString());
	}

	@Operation(tags = "RestapiCallController", summary = "Search internal grade in master internal grade list. ", description = "Search by key")
	@GetMapping("/v1/master-internalgrade/{key}")
	public ResponseEntity<String> fetchMasterInternalGrade(@PathVariable Optional<String> key) {
		List<MasterInternalGrade> internalGradeList = idao.fetchMasterInternalGrade(
				key.orElseThrow(() -> new CustomException("key can not be null", HttpStatus.NOT_FOUND)),
				PageRequest.of(0, 8));
		JSONArray array = new JSONArray();
		for (MasterInternalGrade g : internalGradeList) {
			array.put(g.toJSON());
		}
		return ResponseEntity.ok(array.toString());
	}

	@Operation(tags = "RestapiCallController", summary = "get all Ferro Alloy Specification list. ", description = "Ferro Alloy Specification")
	@GetMapping("/v1/ferro-alloy-specification")
	public ResponseEntity<Map<String, List<MasterFerroAlloySpecification>>> ferroAlloySpecification() {
		Map<String, List<MasterFerroAlloySpecification>> groupedMaterials = ferroDao.findAll().stream()
				.collect(Collectors.groupingBy(MasterFerroAlloySpecification::getMaterialCode));
		return ResponseEntity.ok(groupedMaterials);
	}
	
//	@Operation(tags = "RestapiCallController", summary = "Send mail. ", description = "Send mail to sms")
//	@GetMapping("/v1/send-mail")
//	public void sendMailSms() {
//		mailService.sendEmail(null, "sajan.yadav@jindalsteel.com", "hi", "hello");
//	}
	

}
