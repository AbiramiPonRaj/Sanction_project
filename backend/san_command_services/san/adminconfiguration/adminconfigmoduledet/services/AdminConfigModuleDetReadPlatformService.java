package com.ponsun.san.adminconfiguration.adminconfigmoduledet.services;


import com.ponsun.san.adminconfiguration.adminconfigmoduledet.domain.AdminConfigModuleDet;

import java.util.List;

public interface AdminConfigModuleDetReadPlatformService {

    AdminConfigModuleDet fetchAdminConfigModuleDetById(Integer id);
    List <AdminConfigModuleDet> fetchAllAdminConfigModuleDet();
}
