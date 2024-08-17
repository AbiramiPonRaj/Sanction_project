package com.ponsun.san.aliases.alias.services;

import com.ponsun.san.aliases.alias.domain.Alias;

import java.util.List;

public interface AliasReadPlatformService {
    List<Alias> fetchAllAlias();

    Alias fetchAliasById(Integer id);
}
