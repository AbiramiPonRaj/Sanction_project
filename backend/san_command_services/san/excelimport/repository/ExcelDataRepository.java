package com.ponsun.san.excelimport.repository;

import com.ponsun.san.excelimport.entity.ExcelData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ExcelDataRepository extends JpaRepository<ExcelData, Integer> {
    Optional<ExcelData> findByName(String name);
}
