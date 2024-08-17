package com.ponsun.san.ofac.Aliases.services;

import com.ponsun.san.ofac.Aliases.data.AliasesData;

import java.util.List;

public interface AliasesWriteService {
    List<AliasesData> fetchAllAliasesData(Integer id);

}
