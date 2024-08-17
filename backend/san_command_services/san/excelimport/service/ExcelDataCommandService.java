package com.ponsun.san.excelimport.service;

import com.ponsun.san.excelimport.dto.ExcelDataDto;
import com.ponsun.san.excelimport.entity.ExcelData;
import com.ponsun.san.excelimport.repository.ExcelDataRepository;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ExcelDataCommandService {
    private final ExcelDataRepository excelDataRepository;

    public ExcelDataCommandService(ExcelDataRepository excelDataRepository) {
        this.excelDataRepository = excelDataRepository;
    }

    public ExcelData save(ExcelDataDto input) {
        ExcelData excelData = new ExcelData();
        excelData.setName(input.getName());
        excelData.setType(input.getType());
        excelData.setScore(input.getScore());
        excelData.setCountry(input.getCountry());
        return excelDataRepository.save(excelData);
    }
    @Transactional
    public Response saveBulkData(List<Map<String, Object>> rows) {
        try {
            long totalRecordSaved = 0L;
            System.out.println("Started saveBulk");
            long startTime = System.currentTimeMillis();
            for (Map<String, Object> row : rows) {

                final String name = ((String) row.get("name")).trim();
                System.out.println(name);
                String score = ((String) row.get("score"));
                System.out.println(score);
                final String type = ((String) row.get("type")).trim();
                System.out.println(type);
                final String country = ((String) row.get("country")).trim();
                System.out.println(country);


                ExcelDataDto excelDataDto = new ExcelDataDto();
                excelDataDto.setName(name);
                excelDataDto.setScore(Double.parseDouble(score));
                excelDataDto.setType(type);
                excelDataDto.setCountry(country);

                ExcelData excelData = ExcelData.create(excelDataDto);

                excelData = excelDataRepository.saveAndFlush(excelData);
                log.debug("END of saveBulkData(), id = {}", excelData.getId());
                totalRecordSaved = totalRecordSaved + 1;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Total milli seconds taken " + (endTime - startTime));
            return new Response(totalRecordSaved);
        } catch (DataIntegrityViolationException e) {
            log.error("Error while saveBulkData {}", e.getMessage());
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }

    }

}
