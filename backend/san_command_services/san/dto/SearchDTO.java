package com.ponsun.san.dto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SearchDTO {
    //private int stateId;
    private String name;
    private double matching_score;
    private int listID;
    private int partySubTypeID;
    private int countryId;

    public SearchDTO(String name, double matching_score, int listID, int partySubTypeID, int countryId) {
        this.name = name;
        this.matching_score = matching_score;
        this.listID = listID;
        this.partySubTypeID = partySubTypeID;
        this.countryId = countryId;
    }
    public static SearchDTO newInstance(String name, double matching_score, int listID, int partySubTypeID, int countryId){
        return new SearchDTO(name, matching_score,listID,partySubTypeID,countryId);
    }
}
