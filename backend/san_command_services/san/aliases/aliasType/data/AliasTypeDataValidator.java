package com.ponsun.san.aliases.aliasType.data;




import com.ponsun.san.aliases.aliasType.request.CreateAliasTypeRequest;
import com.ponsun.san.aliases.aliasType.request.UpdateAliasTypeRequest;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AliasTypeDataValidator {
    public void validateSaveAliasTypeData(final CreateAliasTypeRequest request){
        if(request.getText() == null || request.getText().equals("")){
            throw new EQAS_SAN_ApplicationException("Text parameter required");
        }
    }
    public void validateUpdateAliasTypeData(final UpdateAliasTypeRequest request){
        if(request.getText() == null || request.getText().equals("")){
            throw new EQAS_SAN_ApplicationException("Text parameter required");
        }
    }
}
