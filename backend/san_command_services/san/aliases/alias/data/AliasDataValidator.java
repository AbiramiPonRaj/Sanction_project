package com.ponsun.san.aliases.alias.data;




import com.ponsun.san.aliases.alias.request.CreateAliasRequest;
import com.ponsun.san.aliases.alias.request.UpdateAliasRequest;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AliasDataValidator {
    public void validateSaveAliasData(final CreateAliasRequest request){
        if(request.getPrimary() == null || request.getPrimary().equals("")){
            throw new EQAS_SAN_ApplicationException("Primary parameter required");
        }
    }
    public void validateUpdateAliasData(final UpdateAliasRequest request){
        if(request.getPrimary() == null || request.getPrimary().equals("")){
            throw new EQAS_SAN_ApplicationException("Primary parameter required");
        }
    }
}
