package com.ponsun.san.searchLifcycle.rif.services;

import com.ponsun.san.searchLifcycle.rif.data.LevelFourData;

import java.util.List;

public interface LevelFourWritePlatformService {
    List<LevelFourData> fetchAllLevelFourData(Integer levelId,Integer levelIds);
}
