package com.ponsun.san.searchLifcycle.hitcaselifecycle.services;

import com.ponsun.san.searchLifcycle.hitcaselifecycle.domain.HitCaseLifeCycleRepositoryWrapper;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.domain.HitcaseLifecycle;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.domain.HitcaseLifecycleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HitCaseLifeCycleReadPlatformServiceIml implements HitCaseLifeCycleReadPlatformService {
    private final HitCaseLifeCycleRepositoryWrapper hitCaseLifeCycleRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final HitcaseLifecycleRepository hitcaseLifecycleRepository;
    @Override
    public HitcaseLifecycle fetchHitcaseLifeById(Integer id){

        return this.hitcaseLifecycleRepository.findById(id).get();

        }
        @Override
        public List<HitcaseLifecycle> fetchAllHitcaseLife(){
            return this.hitcaseLifecycleRepository.findAll();
        }
    }
