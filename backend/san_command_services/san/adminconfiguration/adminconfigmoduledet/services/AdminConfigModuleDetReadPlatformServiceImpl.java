package com.ponsun.san.adminconfiguration.adminconfigmoduledet.services;


import com.ponsun.san.adminconfiguration.adminconfigmoduledet.domain.AdminConfigModuleDet;
import com.ponsun.san.adminconfiguration.adminconfigmoduledet.domain.AdminConfigModuleDetRepository;
import com.ponsun.san.adminconfiguration.adminconfigmoduledet.domain.AdminConfigModuleDetRepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminConfigModuleDetReadPlatformServiceImpl implements AdminConfigModuleDetReadPlatformService {

    private final AdminConfigModuleDetRepositoryWrapper adminconfigmoduledetRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final AdminConfigModuleDetRepository adminconfigmoduledetRepository;

    @Override
    public AdminConfigModuleDet fetchAdminConfigModuleDetById(Integer id){
        return this.adminconfigmoduledetRepository.findById(id).get();
    }

    @Override
    public List<AdminConfigModuleDet> fetchAllAdminConfigModuleDet(){
        return this.adminconfigmoduledetRepository.findAll();
    }
}
