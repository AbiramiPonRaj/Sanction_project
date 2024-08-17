package com.ponsun.san.searchLifcycle.hitrecordlifecycle.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public  abstract class AbstracthitrecordlifecycleBaseRequest {


    //private Integer id;
    private Integer searchId;
    private Integer hitId;
    private Integer criminalId;
    private Integer caseId;
    private Integer levelId;
    private Integer statusId;
    private Integer uid;
    private LocalDateTime dt;
    private Boolean valid;
    private String remark;

}
