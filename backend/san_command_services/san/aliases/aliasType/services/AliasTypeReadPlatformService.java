package com.ponsun.san.aliases.aliasType.services;

import com.ponsun.san.aliases.aliasType.domain.AliasType;

import java.util.List;

public interface AliasTypeReadPlatformService {
    List<AliasType> fetchAllAliasType();

    AliasType fetchAliasTypeById(Integer id);
}
