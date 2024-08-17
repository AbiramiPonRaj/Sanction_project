package com.ponsun.san.sanction.nameDetails.services;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.sanction.nameDetails.request.CreateNameDetailsRequest;
import com.ponsun.san.sanction.nameDetails.request.UpdateNameDetailsRequest;

public interface NameDetailsWriteService {
    Response createNameDetails(CreateNameDetailsRequest createNameDetailsRequest);

    Response updateNameDetails(Integer id, UpdateNameDetailsRequest updateNameDetailsRequest);

    Response deActivate(Integer id, Integer euid);
}
