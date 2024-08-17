package com.ponsun.san.searchLifcycle.hitrecordlifecycle.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class HitRecordLifeCycleData {

    private Integer id;
    private Integer searchId;
    private Integer hitdataId;
    private Integer criminalId;
    private Integer caseId;
    private Integer levelId;
    private Integer statusId;
    private Integer uid;
    private LocalDateTime dt;
    private Boolean valid;
    private String remark;


    public HitRecordLifeCycleData(final Integer id, final Integer searchId, final Integer hitdataId, final Integer criminalId, final Integer caseId, final Integer levelId, final Integer statusId, final Integer uid, final LocalDateTime dt, final Boolean valid, final String remark) {
        this.id = id;
        this.searchId = searchId;
        this.hitdataId = hitdataId;
        this.criminalId = criminalId;
        this.caseId = caseId;
        this.levelId = levelId;
        this.statusId = statusId;
        this.uid = uid;
        this.dt = dt;
        this.valid = valid;
        this.remark = remark;


    }



    public static HitRecordLifeCycleData newInstance(final Integer id, final Integer searchId, final Integer hitdataId, final Integer criminalId, final Integer caseId, final Integer levelId, final Integer statusId, final Integer uid, final LocalDateTime dt, final Boolean valid, final String remark) {
        return new HitRecordLifeCycleData(id, searchId, hitdataId, criminalId, caseId, levelId, statusId, uid, dt, valid, remark);
    }
}
