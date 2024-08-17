package com.ponsun.san.searchLifcycle.hitrecord.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class HitRecordData {
    private Integer id;
    private Integer searchId;
    private String display;
    private Integer criminalId;
    private Double matchingScore;
    private String name;
    private Integer statusNowId;
    private Integer cycleId;
    private Long uid;
    private Boolean valid;


    public HitRecordData(final  Integer id, final Integer searchId, final String display, final Integer criminalId, final Double matchingScore, final String name, final Integer statusNowId, final Integer cycleId, final Long uid, final Boolean valid) {
        this.id =id ;
        this.searchId = searchId;
        this.display = display;
        this.criminalId = criminalId;
        this.matchingScore = matchingScore;
        this.name = name;
        this.statusNowId = statusNowId;
        this.cycleId = cycleId;
        this.uid = uid;
        this.valid = valid;
    }


    public static HitRecordData newInstance(final  Integer id, final Integer searchId, final String display, final Integer criminalId, final Double matchingScore, final String name, final Integer statusNowId, final Integer cycleId, final Long uid, final Boolean valid) {
        return new HitRecordData(id,searchId, display, criminalId, matchingScore, name, statusNowId,cycleId, uid, valid);
    }
}