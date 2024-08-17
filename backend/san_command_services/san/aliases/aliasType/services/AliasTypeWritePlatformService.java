package com.ponsun.san.aliases.aliasType.services;

import com.ponsun.san.aliases.aliasType.request.CreateAliasTypeRequest;
import com.ponsun.san.aliases.aliasType.request.UpdateAliasTypeRequest;
import com.ponsun.san.infrastructure.utils.Response;

public interface AliasTypeWritePlatformService {
    Response createAliasType(CreateAliasTypeRequest createAliasTypeRequest);

    Response updateAliasType(Integer id, UpdateAliasTypeRequest updateAliasTypeRequest);
}
