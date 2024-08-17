package com.ponsun.san.searchLifcycle.hitcase.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class HitCaseData {
    private Integer id;
    private String display;
    private Integer searchId;
    private Integer hitId;
    private Integer criminalId;
    private Integer levelId;
    private Integer statusNowId;
    private Integer cycleId;
    private Integer uid;
    private String dt;
    private Boolean valid;

    public  HitCaseData (final  Integer id, final String display ,final Integer searchId , final Integer hitId, final Integer criminalId, final Integer levelId, final Integer statusNowId, final Integer cycleId, final Integer uid, final Boolean valid){
        this.id =id ;
        this.display=display;
        this.searchId=searchId;
        this.hitId=hitId;
        this.criminalId=criminalId;
        this.levelId=levelId;
        this.statusNowId=statusNowId;
        this.cycleId=cycleId;
        this.uid=uid;
//        this.dt=dt;
        this.valid=valid;
    }

    public static HitCaseData newInstance(final  Integer id ,final String display ,final Integer searchId , final Integer hitId, final Integer criminalId, final Integer levelId, final Integer statusNowId, final Integer cycleId, final Integer uid,  final Boolean valid){
        return  new HitCaseData(id,display, searchId, hitId, criminalId, levelId, statusNowId, cycleId, uid, valid);
    }
}
