package com.ponsun.san.searchLifcycle.hitrecordtable.services;
import com.ponsun.san.searchLifcycle.hitrecordtable.data.HitRecordDataTableData;

import java.util.List;

public interface HitRecordTableWritePlatformService {
    List<HitRecordDataTableData> fetchAllHitDataTableData(String levelId);
}
