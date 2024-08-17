package com.ponsun.san.adminconfiguration.adminuserauthority.service;


import com.ponsun.san.adminconfiguration.adminuserauthority.domain.AdminUserAuthority;

import java.util.List;

public interface AdminUserAuthorityReadPlatformService {
    AdminUserAuthority fetchAdminUserAuthorityById (Integer id);
    List<AdminUserAuthority> fetchAllAdminUserAuthority();
}
