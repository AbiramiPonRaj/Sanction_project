package com.ponsun.san.adminconfiguration.admingroup.services;


import com.ponsun.san.adminconfiguration.admingroup.request.CreateAdmingroupRequest;
import com.ponsun.san.adminconfiguration.admingroup.request.UpdateAdmingroupRequest;
import com.ponsun.san.infrastructure.utils.Response;

public interface AdmingroupWritePlatformService {

    Response createAdmingroup(CreateAdmingroupRequest createAdmingroupRequest);
    Response updateAdmingroup(Integer id, UpdateAdmingroupRequest updateAdmingroupRequest);
    Response blockAdmingroup(Integer id);
    Response unblockAdmingroup(Integer id);


}
