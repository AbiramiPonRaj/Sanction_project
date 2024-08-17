package com.ponsun.san.master.sanctionsProgram.data;




import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;


import com.ponsun.san.master.sanctionsProgram.request.CreateSanctionsProgramRequest;
import com.ponsun.san.master.sanctionsProgram.request.UpdateSanctionsProgramRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SanctionsProgramDataValidator {
    public void validateSaveSanctionsProgramData(final CreateSanctionsProgramRequest request){
        if(request.getText() == null || request.getText().equals("")){
            throw new EQAS_SAN_ApplicationException("Text parameter required");
        }
    }
    public void validateUpdateSanctionsProgramData(final UpdateSanctionsProgramRequest request){
        if(request.getText() == null || request.getText().equals("")){
            throw new EQAS_SAN_ApplicationException("Text parameter required");
        }
    }
}
