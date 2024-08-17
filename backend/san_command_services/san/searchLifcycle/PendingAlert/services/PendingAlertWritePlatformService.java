package com.ponsun.san.searchLifcycle.PendingAlert.services;

import com.ponsun.san.searchLifcycle.PendingAlert.data.PendingAlertData;

import java.util.List;

public interface PendingAlertWritePlatformService {
    List<PendingAlertData> fetchAllPendingAlertData(String levelId);
}
