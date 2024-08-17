package com.ponsun.san.uiTest.dto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UiSearchDTO {
    private String name;
    private double matching_score;

    public UiSearchDTO(String name, double matching_score) {
        this.name = name;
        this.matching_score = matching_score;
    }
    public static UiSearchDTO newInstance(String name, double matching_score){
        return new UiSearchDTO(name, matching_score);
    }
}
