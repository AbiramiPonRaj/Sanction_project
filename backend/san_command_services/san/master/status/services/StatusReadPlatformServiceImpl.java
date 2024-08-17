package com.ponsun.san.master.status.services;


import com.ponsun.san.master.status.domain.Status;
import com.ponsun.san.master.status.domain.StatusRepository;
import com.ponsun.san.master.status.domain.StatusRepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusReadPlatformServiceImpl implements StatusReadPlatformService {
    private final StatusRepository statusRepository;
    private final JdbcTemplate jdbcTemplate;
    private final StatusRepositoryWrapper statusRepositoryWrapper;

    @Override
    public Status fetchStatusById(Integer id) {
        return this.statusRepository.findById(id).get();
    }

    @Override
    public List<Status> fetchAllStatus() {
        return this.statusRepository.findAll();
    }
}
