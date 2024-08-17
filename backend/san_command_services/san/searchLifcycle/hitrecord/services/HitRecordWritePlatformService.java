package com.ponsun.san.searchLifcycle.hitrecord.services;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitrecord.request.CreateHitRecordRequest;
import com.ponsun.san.searchLifcycle.hitrecord.request.UpdateHitRecordRequest;

public interface HitRecordWritePlatformService {
    Response createHitData(CreateHitRecordRequest createHitDataRequest);
    Response updateHitdata(Integer id, UpdateHitRecordRequest updateHitDataRequest);
    Response blockHitData(Integer id);
    Response unblockHitData(Integer id);
}