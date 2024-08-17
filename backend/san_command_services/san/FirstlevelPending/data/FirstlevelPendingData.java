package com.ponsun.san.FirstlevelPending.data;

import lombok.Data;

@Data
public class FirstlevelPendingData {
    private String name;
    private Double matching_score;
    private String hitName;
    private Double hitScore;
    private Integer hitId;
    private Integer searchId;
    private Integer lifcycleSearchId;
    private Integer recId;
    private Integer fileType;

    public FirstlevelPendingData(String name, Double matching_score, String hitName, Double hitScore, Integer hitId, Integer searchId, Integer lifcycleSearchId, Integer recId, Integer fileType) {
        this.name = name;
        this.matching_score = matching_score;
        this.hitName = hitName;
        this.hitScore = hitScore;
        this.hitId = hitId;
        this.searchId = searchId;
        this.lifcycleSearchId = lifcycleSearchId;
        this.recId = recId;
        this.fileType = fileType;
    }
    public static FirstlevelPendingData newInstance(String name, Double matching_score, String hitName, Double hitScore, Integer hitId, Integer searchId, Integer lifcycleSearchId, Integer recId, Integer fileType){
        return new FirstlevelPendingData(name,matching_score,hitName,hitScore,hitId,searchId,lifcycleSearchId,recId,fileType);
    }
}
