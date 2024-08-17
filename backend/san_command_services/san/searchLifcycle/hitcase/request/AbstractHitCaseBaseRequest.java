package com.ponsun.san.searchLifcycle.hitcase.request;

import lombok.Data;

@Data
public class AbstractHitCaseBaseRequest {
    private Integer id;
    private String display;
    private Integer searchId;
    private Integer hitId;
    private Integer criminalId;
    private Integer levelId;
    private Integer statusNowId;
    private Integer cycleId;
    private Integer uid;
    private String remark;
//    private String dt;
    private Boolean valid;
}
