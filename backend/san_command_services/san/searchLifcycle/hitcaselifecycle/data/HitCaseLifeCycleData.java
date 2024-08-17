package com.ponsun.san.searchLifcycle.hitcaselifecycle.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class HitCaseLifeCycleData {
    private  Integer id;
    private  Integer caseId;
    private Integer levelId;
    private String remark;
    private Integer statusId;
    private Integer uid;
    private String dt;
    private Boolean valid;

    public HitCaseLifeCycleData(final Integer id , final Integer caseId, final Integer levelId, final String remark, final Integer statusId, final Integer uid, final String dt, final Boolean valid ) {
        this.id =id;
        this.caseId = caseId;
        this.levelId = levelId;
        this.remark = remark;
        this.statusId = statusId;
        this.dt = dt;
        this.valid = valid;
        this.uid = uid;
    }

    public static HitCaseLifeCycleData newInstance(final Integer id, final Integer caseId, final Integer levelId, final String remark, final Integer statusId, final Integer uid, final String dt, final Boolean valid ) {
        return new HitCaseLifeCycleData(id,caseId,levelId, remark, statusId,uid,dt,valid);
    }
}
