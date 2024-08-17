package com.ponsun.san.adminconfiguration.admingroup.services;


import com.ponsun.san.adminconfiguration.admingroup.domain.Admingroup;
import com.ponsun.san.adminconfiguration.admingroup.domain.AdmingroupRepository;
import com.ponsun.san.adminconfiguration.admingroup.domain.AdmingroupRepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmingroupReadPlatformServiceImpl implements AdmingroupReadPlatformService {
    private final AdmingroupRepositoryWrapper admingroupRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final AdmingroupRepository admingroupRepository;

    @Override
    public Admingroup fetchAdmingroupById(Integer id) {

        return this.admingroupRepository.findById(id).get();

    }

    @Override
    public List<Admingroup> fetchAllAdmingroup() {
        return this.admingroupRepository.findAll();
    }
}

