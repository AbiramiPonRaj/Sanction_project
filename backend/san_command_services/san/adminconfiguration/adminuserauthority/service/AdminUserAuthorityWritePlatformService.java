package com.ponsun.san.adminconfiguration.adminuserauthority.service;


import com.ponsun.san.adminconfiguration.adminuserauthority.request.CreateAdminUserAuthorityRequest;
import com.ponsun.san.adminconfiguration.adminuserauthority.request.UpdateAdminUserAuthorityRequest;
import com.ponsun.san.infrastructure.utils.Response;

public interface AdminUserAuthorityWritePlatformService {
    Response createAdminUserAuthority(CreateAdminUserAuthorityRequest createAdminUserAuthorityRequest);
    Response updateAdminUserAuthority(Integer id, UpdateAdminUserAuthorityRequest updateAdminUserAuthorityRequest);
    Response blockAdminUserAuthority(Integer id);
    Response unblockAdminUserAuthority(Integer id);
}
