package com.ponsun.san.searchLifcycle.hitrecordlifecycle.services;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.request.CreateHitRecordLifecycleRequest;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.request.UpdateHitdataLifecycleRequest;

public interface HitRecordLifecycleWritePlatformService {
    Response createHitdataLifecycle(CreateHitRecordLifecycleRequest createHitRecordLifecycleRequest);
    //Response l2_createHitdataLifecycle(CreateHitdataLifecycle createHitdataLifecycle);
    Response updateHitdataLifecycle(Integer id, UpdateHitdataLifecycleRequest updateHitdataLifecycleRequest);
    Response blockHitdataLifecycle(Integer id);
    Response unblockHitdataLifecycle(Integer id);
}
