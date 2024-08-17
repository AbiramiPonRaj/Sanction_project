package com.ponsun.san.adminconfiguration.adminconfigmoduledet.data;


import com.ponsun.san.adminconfiguration.adminconfigmoduledet.request.CreateAdminConfigModuleDetRequest;
import com.ponsun.san.adminconfiguration.adminconfigmoduledet.request.UpdateAdminConfigModuleDetRequest;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminConfigModuleDetDataValidator {
    public void validateSaveModuleDet(final CreateAdminConfigModuleDetRequest request) {
        if (request.getName() == null || request.getName().equals("")) {
            throw new EQAS_SAN_ApplicationException("ModuleName parameter required");
        }
    }
    public void validateUpdateModuleDet(final UpdateAdminConfigModuleDetRequest request) {
        if (request.getName() == null || request.getName().equals("")) {
            throw new EQAS_SAN_ApplicationException("ModuleName parameter required");
        }
    }
}