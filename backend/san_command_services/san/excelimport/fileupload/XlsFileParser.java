package com.ponsun.san.excelimport.fileupload;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Slf4j
public class XlsFileParser implements FileParser {

    private final String fileName;

    private final String directoryPath;

    public XlsFileParser(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }

    @Override
    public List<Map<String, Object>> parseExcelData() {
        log.debug("Start of parseExcelData()");
        List<Map<String, Object>> results = new ArrayList<>();
        File xlsFile;
        FileInputStream fis = null;
        try {
            xlsFile = new File(this.directoryPath + File.separator + fileName);
            fis = new FileInputStream(xlsFile);
//             fis = OPCPackage.open(new File(this.directoryPath + File.separator + fileName));
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIterator = mySheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // Skip header
                if (row.getRowNum() == 0) {
                    continue;
                }
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                final Map<String, Object> resultData = new HashMap<>();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (0 == cell.getColumnIndex()) {
                        if (cell.getCellType().name().equals("STRING")) {
                            resultData.put("name", cell.getStringCellValue());
                        }
                        if (cell.getCellType().name().equals("DOUBLE")) {
                            resultData.put("name", Double.valueOf(cell.getNumericCellValue()));
                        }
                        if (cell.getCellType().name().equals("NUMERIC")) {
                            resultData.put("name", String.valueOf(cell.getNumericCellValue()));
                        }
                    }

                    if (1 == cell.getColumnIndex()) {
                        if (cell.getCellType().name().equals("STRING")) {
                            resultData.put("score", cell.getStringCellValue());
                        }
                        if (cell.getCellType().name().equals("DOUBLE")) {
                            resultData.put("score", Double.valueOf(cell.getNumericCellValue()));
                        }
                        if (cell.getCellType().name().equals("NUMERIC")) {
                            resultData.put("score", String.valueOf(cell.getNumericCellValue()));
                        }
                    }
                    if (2 == cell.getColumnIndex()) {
                        if (cell.getCellType().name().equals("STRING")) {
                            resultData.put("type", cell.getStringCellValue());
                        }
                        if (cell.getCellType().name().equals("DOUBLE")) {
                            resultData.put("type", Double.valueOf(cell.getNumericCellValue()));
                        }
                        if (cell.getCellType().name().equals("NUMERIC")) {
                            resultData.put("type", String.valueOf(cell.getNumericCellValue()));
                        }
                    }

                    if (3 == cell.getColumnIndex()) {
                        if (cell.getCellType().name().equals("STRING")) {
                            resultData.put("country", cell.getStringCellValue());
                        }
                        if (cell.getCellType().name().equals("DOUBLE")) {
                            resultData.put("country", Double.valueOf(cell.getNumericCellValue()));
                        }
                        if (cell.getCellType().name().equals("NUMERIC")) {
                            resultData.put("country", String.valueOf(cell.getNumericCellValue()));
                        }
                    }
                }
                log.debug("END of parseExcelData()");
                results.add(resultData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error while parsing file data {} ", e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

}
