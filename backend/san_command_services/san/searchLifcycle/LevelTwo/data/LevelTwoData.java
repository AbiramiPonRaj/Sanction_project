package com.ponsun.san.searchLifcycle.LevelTwo.data;

import lombok.Data;

@Data
public class LevelTwoData {
    private Integer searchId;
    private Integer criminalId;
    private Integer hitId;
    private Integer caseId;
    private String criminalName;
    private Integer matchScore;
    private String country;
    private String state;
    private String dob;

public LevelTwoData(final Integer searchId,final Integer criminalId,final Integer hitId,final Integer caseId,final String criminalName,final Integer matchScore,final String country,final String state,final String dob){
    this.searchId = searchId;
    this.criminalId = criminalId;
    this.hitId = hitId;
    this.caseId = caseId;
    this.criminalName = criminalName;
    this.matchScore = matchScore;
    this.country = country;
    this.state = state;
    this.dob = dob;
}
public static LevelTwoData newInstance(final Integer searchId,final Integer criminalId,final Integer hitId,final Integer caseId,final String criminalName,final Integer matchScore,final String country,final String state,final String dob){
    return new LevelTwoData(searchId, criminalId, hitId, caseId, criminalName, matchScore, country, state, dob);
}

}
