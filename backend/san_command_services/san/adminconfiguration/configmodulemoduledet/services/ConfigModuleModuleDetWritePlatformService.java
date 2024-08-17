package com.ponsun.san.adminconfiguration.configmodulemoduledet.services;



import com.ponsun.san.adminconfiguration.configmodulemoduledet.data.ConfigModuleModuleDetData;

import java.util.List;

public interface ConfigModuleModuleDetWritePlatformService {
    List<ConfigModuleModuleDetData> fetchAllListofAlertData();
}
