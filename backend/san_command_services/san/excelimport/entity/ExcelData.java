package com.ponsun.san.excelimport.entity;

import com.ponsun.san.excelimport.dto.ExcelDataDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "excel_data")
@Entity
@Data
public class ExcelData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column( nullable = false)
    private Double score;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String country;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static ExcelData create(final ExcelDataDto excelDataDto) {
        final ExcelData excelData = new ExcelData();
        excelData.setCreatedAt(LocalDateTime.now());
        excelData.setUpdatedAt(LocalDateTime.now());
        excelData.setName(excelDataDto.getName());
        excelData.setScore(excelDataDto.getScore());
        excelData.setType(excelDataDto.getType());
        excelData.setCountry(excelDataDto.getCountry());
        return excelData;
    }
}
