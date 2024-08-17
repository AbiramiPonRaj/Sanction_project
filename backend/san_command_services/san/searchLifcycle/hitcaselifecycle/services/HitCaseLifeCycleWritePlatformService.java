package com.ponsun.san.searchLifcycle.hitcaselifecycle.services;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.request.CreateHitCaseLifeCycleRequest;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.request.UpdateHitCaseLifeCycleRequest;

public interface HitCaseLifeCycleWritePlatformService {
    Response createHitCaseLifeCycle(CreateHitCaseLifeCycleRequest createHitCaseLifeCycleRequest);


    Response updateHitCaseLifeCycle(Integer id, UpdateHitCaseLifeCycleRequest updateHitCaseLifeCycleRequest);



    Response blockHitCaseLifeCycle(Integer id);

    Response unblockHitCaseLifeCycle(Integer id);
}
