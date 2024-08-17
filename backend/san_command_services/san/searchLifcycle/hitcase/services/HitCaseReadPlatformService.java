package com.ponsun.san.searchLifcycle.hitcase.services;

import com.ponsun.san.searchLifcycle.hitcase.domain.Hitcase;

import java.util.List;

public interface HitCaseReadPlatformService {
    List<Hitcase> fetchAllHitcase();
    Hitcase fetchHitcaseById(Integer id);



}
