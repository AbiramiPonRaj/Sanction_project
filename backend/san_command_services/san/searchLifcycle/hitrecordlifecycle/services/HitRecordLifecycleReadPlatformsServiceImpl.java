package com.ponsun.san.searchLifcycle.hitrecordlifecycle.services;

import com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain.HitRecordLifecycle;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain.HitRecordlifecycleRepository;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain.HitRecordlifecycleRepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HitRecordLifecycleReadPlatformsServiceImpl implements HitRecordLifecycleReadPlatformService {
    private final HitRecordlifecycleRepositoryWrapper hitRecordlifecycleRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final HitRecordlifecycleRepository hitRecordlifecycleRepository;


    @Override
    public HitRecordLifecycle fetchAllHitdataLifecycle(Integer id) {
        return null;
    }

    @Override
    public HitRecordLifecycle fetchHitdataLifecycleById(Integer id){
        return this.hitRecordlifecycleRepository.findById(id).get();
    }

    @Override
    public List<HitRecordLifecycle> fetchAllHitdataLifecycle(){
        return this.hitRecordlifecycleRepository.findAll();
    }

}
