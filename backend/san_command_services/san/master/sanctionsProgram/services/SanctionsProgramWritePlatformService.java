package com.ponsun.san.master.sanctionsProgram.services;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.master.sanctionsProgram.request.CreateSanctionsProgramRequest;
import com.ponsun.san.master.sanctionsProgram.request.UpdateSanctionsProgramRequest;

public interface SanctionsProgramWritePlatformService {
    Response createSanctionsProgram(CreateSanctionsProgramRequest createSanctionsProgramRequest);

    Response updateSanctionsProgram(Integer id, UpdateSanctionsProgramRequest updateSanctionsProgramRequest);
}
