package com.ponsun.san.master.status.services;

import com.ponsun.san.master.status.domain.Status;

import java.util.List;

public interface StatusReadPlatformService {
    Status fetchStatusById(Integer id);

    List<Status> fetchAllStatus();
}
