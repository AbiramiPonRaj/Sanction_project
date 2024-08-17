package com.ponsun.san.aliases.aliasTypeValues.services;

import com.ponsun.san.aliases.aliasTypeValues.domain.AliasTypeValues;

import java.util.List;

public interface AliasTypeValuesReadPlatformService {
    AliasTypeValues fetchAliasTypeValuesById(Integer id);

    List<AliasTypeValues> fetchAllAliasTypeValues();
}
