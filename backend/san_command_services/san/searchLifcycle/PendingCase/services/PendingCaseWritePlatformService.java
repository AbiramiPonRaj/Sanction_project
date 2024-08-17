package com.ponsun.san.searchLifcycle.PendingCase.services;



import com.ponsun.san.searchLifcycle.PendingCase.data.PendingCaseData;

import java.util.List;

public interface PendingCaseWritePlatformService {
    List<PendingCaseData> fetchAllPendingCaseData();
    List<PendingCaseData> fetchByLfourPendingCase();

}
