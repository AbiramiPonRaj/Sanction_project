package com.ponsun.san.searchLifcycle.PendingAlert.data;

import lombok.Data;

@Data
public class PendingAlertData {
    private Integer searchId;
    private Integer hitId;
    private Integer criminalId;
    private String criminalName;
    private Integer matchingScore;
    private String remark;
    private String createdAt;


    public PendingAlertData(final Integer searchId,final Integer hitId,final Integer criminalId,final String name,final Integer matchingScore,final String remark,final String createdAt){
        this.searchId = searchId;
        this.hitId = hitId;
        this.criminalId = criminalId;
        this.criminalName = name;
        this.matchingScore = matchingScore;
        this.remark = remark;
        this.createdAt = createdAt;

    }
    public  static PendingAlertData newInstance(final Integer searchId,final Integer hitId,final Integer criminalId,final String criminalName,final Integer matchingScore,final String remark,final String createdAt){
        return new PendingAlertData(searchId, hitId, criminalId, criminalName, matchingScore, remark,createdAt);
    }
}
