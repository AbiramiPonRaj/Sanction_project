package com.ponsun.san.searchLifcycle.hitrecordlifecycle.services;


import com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain.HitRecordLifecycle;

import java.util.List;

public interface HitRecordLifecycleReadPlatformService {


    HitRecordLifecycle fetchAllHitdataLifecycle(Integer id);

    HitRecordLifecycle fetchHitdataLifecycleById(Integer id);

    List<HitRecordLifecycle> fetchAllHitdataLifecycle();
}
