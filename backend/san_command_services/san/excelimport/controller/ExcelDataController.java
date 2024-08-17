package com.ponsun.san.excelimport.controller;
import com.ponsun.san.excelimport.fileupload.XlsFileParser;
import com.ponsun.san.excelimport.service.ExcelDataCommandService;
import com.ponsun.san.infrastructure.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/excel")
@RequiredArgsConstructor
@Slf4j
public class ExcelDataController {

    private final ExcelDataCommandService excelDataCommandService;

    @PostMapping("/tableBulkImport")
    public Response saveExcelDataFromFile() {
        log.debug("START of saveExcelDataFfromFile() ");
        XlsFileParser xlsFileParser = new XlsFileParser("D:\\workspace\\file_upload", "excel_data.xlsx");
        final List<Map<String, Object>> data = xlsFileParser.parseExcelData();
        Response response = excelDataCommandService.saveBulkData(data);
        log.debug("END of saveExcelDataFfromFile()");
        return response;
    }
}
