package com.ponsun.san.searchLifcycle.hitrecord.request;

import lombok.Data;

@Data

public class AbstractHitRecordBaseRequest {
    private Integer id;
    private Integer searchId;
    private String display;
    private Integer criminalId;
    private Integer fileType;
    private Double matchingScore;
    private String name;
    private Integer statusNowId;
    private Integer cycleId;
    private Long uid;
    private Boolean valid;
}