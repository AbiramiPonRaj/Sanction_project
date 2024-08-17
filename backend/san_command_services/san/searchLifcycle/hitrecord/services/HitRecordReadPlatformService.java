package com.ponsun.san.searchLifcycle.hitrecord.services;

import com.ponsun.san.searchLifcycle.hitrecord.data.HitRecordData;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecord;

import java.util.List;

public interface HitRecordReadPlatformService {
    HitRecord fetchAHitDataById(Integer id);
    List<HitRecordData> fetchAllHitDataById(Integer searchId);

    //HitData fetchAllHitId(Integer criminalId,Integer searchId);
    List<HitRecord> fetchAll();

}



