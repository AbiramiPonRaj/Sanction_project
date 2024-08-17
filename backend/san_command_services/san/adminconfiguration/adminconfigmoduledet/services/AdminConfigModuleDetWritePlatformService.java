package com.ponsun.san.adminconfiguration.adminconfigmoduledet.services;


import com.ponsun.san.adminconfiguration.adminconfigmoduledet.request.CreateAdminConfigModuleDetRequest;
import com.ponsun.san.adminconfiguration.adminconfigmoduledet.request.UpdateAdminConfigModuleDetRequest;
import com.ponsun.san.infrastructure.utils.Response;

public interface AdminConfigModuleDetWritePlatformService {
    Response createAdminConfigModuleDet(CreateAdminConfigModuleDetRequest createAdminConfigModuleDetRequest);
    Response updateAdminConfigModuleDet(Integer id, UpdateAdminConfigModuleDetRequest updateAdminConfigModuleDetRequest);

//    Response deactive(Integer id, Integer euid);
    Response blockAdminConfigModuleDet(Integer id);
    Response unblockAdminConfigModuleDet(Integer id);
}
