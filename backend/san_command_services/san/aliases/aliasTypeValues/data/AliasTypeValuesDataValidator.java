package com.ponsun.san.aliases.aliasTypeValues.data;




import com.ponsun.san.aliases.aliasTypeValues.request.CreateAliasTypeValuesRequest;
import com.ponsun.san.aliases.aliasTypeValues.request.UpdateAliasTypeValuesRequest;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AliasTypeValuesDataValidator {
    public void validateSaveAliasTypeValuesData(final CreateAliasTypeValuesRequest request){
        if(request.getPrimaryKey() == null || request.getPrimaryKey().equals("")){
            throw new EQAS_SAN_ApplicationException("PrimaryKey parameter required");
        }
    }
    public void validateUpdateAliasTypeValuesData(final UpdateAliasTypeValuesRequest request){
        if(request.getPrimaryKey() == null || request.getPrimaryKey().equals("")){
            throw new EQAS_SAN_ApplicationException("PrimaryKey parameter required");
        }
    }
}
