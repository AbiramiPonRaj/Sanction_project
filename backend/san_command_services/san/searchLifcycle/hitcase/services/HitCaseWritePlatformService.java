package com.ponsun.san.searchLifcycle.hitcase.services;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitcase.request.CreateHitCaseRequest;
import com.ponsun.san.searchLifcycle.hitcase.request.UpdateHitCaseRequest;

public interface HitCaseWritePlatformService {
    Response CreateHitcase(CreateHitCaseRequest createHitCaseRequest);

    Response updateHitcase(Integer id, UpdateHitCaseRequest updateHitCaseRequest);

    Response blockHitcase(Integer id);

    Response unblockHitcase(Integer id);
}
