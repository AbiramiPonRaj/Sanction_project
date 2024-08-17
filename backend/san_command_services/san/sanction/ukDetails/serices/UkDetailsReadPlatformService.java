package com.ponsun.san.sanction.ukDetails.serices;

import com.ponsun.san.sanction.ukDetails.data.UkAliasDetailsData;
import com.ponsun.san.sanction.ukDetails.data.UkCityDetailsData;

import java.util.List;

public interface UkDetailsReadPlatformService {
    List<UkAliasDetailsData> fetchAllUkAliasDetailsData(Integer Group_ID);

    List<UkCityDetailsData> fetchAllUkCityDetailsData(Integer Group_ID);
}
