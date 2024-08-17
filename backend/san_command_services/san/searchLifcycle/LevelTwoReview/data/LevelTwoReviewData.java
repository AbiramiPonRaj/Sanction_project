package com.ponsun.san.searchLifcycle.LevelTwoReview.data;

import lombok.Data;

@Data
public class LevelTwoReviewData {
    private Integer caseId;
    private Integer hitId;
    private Integer criminalId;
    private String criminalName;
    private Integer matchingScore;
    private String remark;
    private String createdAt;
    private Integer searchId;

    public LevelTwoReviewData(final Integer caseId,final Integer hitId,final Integer criminalId,final String criminalName,final Integer matchingScore,final String remark,final String createdAt,final Integer searchId){
        this.caseId = caseId;
        this.hitId = hitId;
        this.criminalId = criminalId;
        this.criminalName = criminalName;
        this.matchingScore = matchingScore;
        this.remark = remark;
        this.createdAt = createdAt;
        this.searchId = searchId;
    }
    public static  LevelTwoReviewData newInstance(final Integer caseId,final Integer hitId,final Integer criminalId,final String criminalName,final Integer matchingScore,final String remark,final String createdAt,final Integer searchId){
        return new LevelTwoReviewData(caseId, hitId, criminalId, criminalName, matchingScore, remark, createdAt, searchId);
    }
}
