package com.ponsun.san.accessPermission.services;

import com.ponsun.san.accessPermission.data.AccessPermissionData;

import java.util.List;

public interface AccessPermissionWritePlatformService {
    List<AccessPermissionData> fetchAllAccessPermissionData(String uid);
}
