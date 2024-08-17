package com.ponsun.san.aliases.alias.services;

import com.ponsun.san.aliases.alias.request.CreateAliasRequest;
import com.ponsun.san.aliases.alias.request.UpdateAliasRequest;
import com.ponsun.san.infrastructure.utils.Response;

public interface AliasWritePlatformService {
    Response createAlias(CreateAliasRequest createAliasRequest);

    Response updateAlias(Integer id, UpdateAliasRequest updateAliasRequest);
}
