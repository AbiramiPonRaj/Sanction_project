package com.ponsun.san.searchLifcycle.hitcase.services;

import com.ponsun.san.searchLifcycle.hitcase.domain.HitCaseRepositoryWapper;
import com.ponsun.san.searchLifcycle.hitcase.domain.Hitcase;
import com.ponsun.san.searchLifcycle.hitcase.domain.HitcaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HitCaseReadPlatformServiceImpl implements HitCaseReadPlatformService {
    private final HitCaseRepositoryWapper hitCaseRepositoryWapper;

    private final JdbcTemplate jdbcTemplate;

    private final HitcaseRepository hitcaseRepository;

    @Override
    public Hitcase fetchHitcaseById(Integer id){
        return this.hitcaseRepository.findById(id).get();
    }
    @Override
    public List<Hitcase> fetchAllHitcase()
    {
        return this.hitcaseRepository.findAll();
    }

}
