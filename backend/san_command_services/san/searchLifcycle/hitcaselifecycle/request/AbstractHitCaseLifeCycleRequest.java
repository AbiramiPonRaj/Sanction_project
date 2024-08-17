package com.ponsun.san.searchLifcycle.hitcaselifecycle.request;

import lombok.Data;



@Data
public class AbstractHitCaseLifeCycleRequest {


    private Integer caseId;
    private Integer levelId;
    private String remark;
    private Integer statusId;
    private Integer uid;
    private String dt;
    private Boolean valid;
}
