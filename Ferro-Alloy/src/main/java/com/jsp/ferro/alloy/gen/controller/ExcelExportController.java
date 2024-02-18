package com.jsp.ferro.alloy.gen.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ferro.alloy.gen.entity.ExcelGenerator;
import com.jsp.ferro.alloy.qlty.entity.QualityRequestHistory;
import com.jsp.ferro.alloy.qlty.service.QualityHistoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author Sajan Yadav
 * @Date 26-12-2023
 */
@Tag(name = "ExcelExportController", description = "For exporting data in to excel file")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Controller
public class ExcelExportController {
	private final QualityHistoryService qualityHisoryService;
	@GetMapping("/export-to-excel/{id}")
    public void exportIntoExcelFile(HttpServletResponse response,@PathVariable Optional<Integer> id) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=quality-history" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<QualityRequestHistory> listOfRequestHistory =qualityHisoryService.fatchQualityHistoryData(id);
        ExcelGenerator generator = new ExcelGenerator(listOfRequestHistory);
        generator.generateExcelFile(response);
    }

}
