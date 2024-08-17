package com.ponsun.san.ofac.Count.service;
import com.ponsun.san.ofac.Count.data.SanctionDetailData;
import java.util.Map;
import java.util.List;

public interface SanctionDetailReadService {
//    Map<String, SanctionDetailData> fetchSanctionData(Integer entityLogicalId, Integer groupId, Integer dataId);

    SanctionDetailData fetchEuSanctionData(Integer entityLogicalId);
    SanctionDetailData fetchUkSanctionData(Integer Group_id);
    SanctionDetailData fetchUnSanctionData(Integer dataid);

    List<SanctionDetailData> fetchAllEUSanctionData();
    List<SanctionDetailData> fetchAllUKSanctionData();
    List<SanctionDetailData> fetchAllUnSanctionData();

    SanctionDetailData fetchSanctionData(Integer ids,Integer SanType);

}
