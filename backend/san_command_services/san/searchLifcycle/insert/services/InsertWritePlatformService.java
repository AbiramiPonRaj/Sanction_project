package com.ponsun.san.searchLifcycle.insert.services;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.PendingCase.data.PendingCaseData;
import com.ponsun.san.searchLifcycle.hitcase.request.CreateHitCaseRequest;

public interface InsertWritePlatformService {
    Response CreateHitCase_HitrecordLifeCycle(CreateHitCaseRequest createHitCaseRequest);
    Response insertPendingCaseData(PendingCaseData pendingCaseData);

    void updateValidFlag(Integer searchId, Integer criminalId,Integer hitId);
    void updateHitDataStatus(Integer hitId);
}
