package com.ponsun.san.adminconfiguration.admingroup.data;


import com.ponsun.san.adminconfiguration.admingroup.request.CreateAdmingroupRequest;
import com.ponsun.san.adminconfiguration.admingroup.request.UpdateAdmingroupRequest;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdmingroupDataValidator {
    public void validateSaveAdmingroup(final CreateAdmingroupRequest request) {
        if (request.getName() == null || request.getName().equals("")) {
            throw new EQAS_SAN_ApplicationException("ModuleName parameter required");
        }
    }
    public void validateUpdateAdmingroup(final UpdateAdmingroupRequest request) {
        if (request.getName() == null || request.getName().equals("")) {
            throw new EQAS_SAN_ApplicationException("ModuleName parameter required");
        }
    }
}
