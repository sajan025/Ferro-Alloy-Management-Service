package com.jsp.ferro.alloy.gen.entity;


import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jsp.ferro.alloy.qlty.entity.ChemicalCompositionHistory;
import com.jsp.ferro.alloy.qlty.entity.QualityRequestHistory;


public class ExcelGenerator {

    private List <QualityRequestHistory> qualityRequestList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List < QualityRequestHistory > qualityRequestList) {
        this.qualityRequestList = qualityRequestList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("QualityHistory");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        //createCell(row, 0, "Sno.", style);
        createCell(row, 0, "Min_C", style);
        createCell(row, 1, "Min_Mn", style);
        createCell(row, 2, "Min_Si", style);
        createCell(row, 3, "Min_Cr", style);
        createCell(row, 4, "Min_V", style);
        createCell(row, 5, "Min_Nb", style);
        createCell(row, 6, "Min_Ni", style);
        createCell(row, 7, "Min_Mo", style);
        createCell(row, 8, "Min_Cu", style);
        createCell(row, 9, "Min_Ti", style);
        createCell(row, 10, "Min_Ca", style);
        createCell(row, 11, "Min_Al", style);
        createCell(row, 12, "Min_B", style);
        createCell(row, 13, "Min_P", style);
        createCell(row, 14, "Min_N", style);
        
        createCell(row, 15, "Max_C", style);
        createCell(row, 16, "Max_Mn", style);
        createCell(row, 17, "Max_Si", style);
        createCell(row, 18, "Max_Cr", style);
        createCell(row, 19, "Max_V", style);
        createCell(row, 20, "Max_Nb", style);
        createCell(row, 21, "Max_Ni", style);
        createCell(row, 22, "Max_Mo", style);
        createCell(row, 23, "Max_Cu", style);
        createCell(row, 24, "Max_Ti", style);
        createCell(row, 25, "Max_Ca", style);
        createCell(row, 26, "Max_Al", style);
        createCell(row, 27, "Max_B", style);
        createCell(row, 28, "Max_P", style);
        createCell(row, 29, "Max_N", style);
    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (QualityRequestHistory record: qualityRequestList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            //createCell(row, columnCount++, columnCount++, style);
            createCell(row, columnCount++, record.getCompositionList().get(0).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(1).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(2).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(3).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(4).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(5).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(6).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(7).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(8).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(9).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(10).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(11).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(12).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(13).getMin(), style);
            createCell(row, columnCount++, record.getCompositionList().get(14).getMin(), style);
            
            createCell(row, columnCount++, record.getCompositionList().get(0).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(1).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(2).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(3).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(4).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(5).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(6).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(7).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(8).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(9).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(10).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(11).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(12).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(13).getMax(), style);
            createCell(row, columnCount++, record.getCompositionList().get(14).getMax(), style);


        }
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
