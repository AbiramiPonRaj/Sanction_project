package com.ponsun.san.adminconfiguration.adminuserauthority.service;


import com.ponsun.san.adminconfiguration.adminuserauthority.domain.AdminUserAuthority;
import com.ponsun.san.adminconfiguration.adminuserauthority.domain.AdminUserAuthorityRepository;
import com.ponsun.san.adminconfiguration.adminuserauthority.domain.AdminUserAuthorityRepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminUserAuthorityReadPlatformServiceImpl implements AdminUserAuthorityReadPlatformService{

    private final AdminUserAuthorityRepositoryWrapper adminUserAuthorityRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final AdminUserAuthorityRepository adminUserAuthorityRepository;

    @Override
    public AdminUserAuthority fetchAdminUserAuthorityById(Integer id) {
        return  this.adminUserAuthorityRepository.findById(id).get();
    }
    @Override
    public List<AdminUserAuthority>  fetchAllAdminUserAuthority() {return this.adminUserAuthorityRepository.findAll();}
}
