package com.ponsun.san.excelimport.fileupload;

import java.util.List;
import java.util.Map;

public interface FileParser {
    List<Map<String, Object>> parseExcelData();
}
