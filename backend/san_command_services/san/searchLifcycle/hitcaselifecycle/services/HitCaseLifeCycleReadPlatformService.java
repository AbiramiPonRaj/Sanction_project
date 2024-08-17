package com.ponsun.san.searchLifcycle.hitcaselifecycle.services;

import com.ponsun.san.searchLifcycle.hitcaselifecycle.domain.HitcaseLifecycle;

import java.util.List;

public interface HitCaseLifeCycleReadPlatformService {


    HitcaseLifecycle fetchHitcaseLifeById(Integer id);

    List<HitcaseLifecycle> fetchAllHitcaseLife();
}
