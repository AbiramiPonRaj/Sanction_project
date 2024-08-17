package com.ponsun.san.searchLifcycle.LevelOneReview.data;

import lombok.Data;

@Data
public class LevelOneReviewData {
    private Integer caseId;
    private Integer hitId;
    private Integer criminalId;
    private String criminalName;
    private Integer matchingScore;
    private String remark;
    private String createdAt;
    private Integer searchId;

    public LevelOneReviewData(final Integer caseId, final Integer hitId, final Integer criminalId, final String criminalName, final Integer matchingScore, final String remark, final String createdAt, final Integer searchId){
        this.caseId = caseId;
        this.hitId = hitId;
        this.criminalId = criminalId;
        this.criminalName = criminalName;
        this.matchingScore = matchingScore;
        this.remark = remark;
        this.createdAt = createdAt;
        this.searchId = searchId;
    }
    public static LevelOneReviewData newInstance(final Integer caseId, final Integer hitId, final Integer criminalId, final String criminalName, final Integer matchingScore, final String remark, final String createdAt, final Integer searchId){
        return new LevelOneReviewData(caseId, hitId, criminalId, criminalName, matchingScore, remark, createdAt, searchId);
    }

}
