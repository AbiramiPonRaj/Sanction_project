package com.ponsun.san.adminconfiguration.adminuserrights.services;

import com.ponsun.san.adminconfiguration.adminuserrights.domain.AdminUserRights;
import com.ponsun.san.adminconfiguration.adminuserrights.domain.AdminUserRightsRepository;
import com.ponsun.san.adminconfiguration.adminuserrights.domain.AdminUserRightsRepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminUserRightsReadPlatformServiceImpl implements AdminUserRightsReadPlatformService{

    private final AdminUserRightsRepositoryWrapper adminUserRightsRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final AdminUserRightsRepository adminUserRightsRepository;

    @Override
    public AdminUserRights fetchAdminUserRightsById(Integer id) {

        return (AdminUserRights) this.adminUserRightsRepository.findById(id).get();
    }

    @Override
    public List<AdminUserRights> fetchAllAdminUserRights() {
        return this.adminUserRightsRepository.findAll();
    }

}
