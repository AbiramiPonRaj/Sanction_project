package com.ponsun.san.aliases.aliasTypeValues.services;

import com.ponsun.san.aliases.aliasTypeValues.request.CreateAliasTypeValuesRequest;
import com.ponsun.san.aliases.aliasTypeValues.request.UpdateAliasTypeValuesRequest;
import com.ponsun.san.infrastructure.utils.Response;

public interface AliasTypeValuesWritePlatformService {
    Response createAliasTypeValues(CreateAliasTypeValuesRequest createAliasTypeValuesRequest);

    Response updateAliasTypeValues(Integer id, UpdateAliasTypeValuesRequest updateAliasTypeValuesRequest);
}
