package com.ponsun.san.master.status.data;

import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.master.status.request.CreateStatusRequest;
import com.ponsun.san.master.status.request.UpdateStatusRequest;

public class StatusDataValidator {

    public void validateSaveModuleDet(final CreateStatusRequest request) {
        if (request.getName() == null || request.getName().equals("")) {
            throw new EQAS_SAN_ApplicationException("ModuleName parameter required");
        }
    }
    public void validateUpdateModuleDet(final UpdateStatusRequest request) {
        if (request.getName() == null || request.getName().equals("")) {
            throw new EQAS_SAN_ApplicationException("ModuleName parameter required");
        }
    }
}
