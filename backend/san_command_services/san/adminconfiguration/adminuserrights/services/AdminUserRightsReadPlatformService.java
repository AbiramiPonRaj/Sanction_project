package com.ponsun.san.adminconfiguration.adminuserrights.services;

import com.ponsun.san.adminconfiguration.adminuserrights.domain.AdminUserRights;

import java.util.List;

public interface AdminUserRightsReadPlatformService {

    AdminUserRights fetchAdminUserRightsById (Integer id);
    List<AdminUserRights> fetchAllAdminUserRights();
}
