package com.ponsun.san.master.sanctionsProgram.services;

import com.ponsun.san.master.sanctionsProgram.domain.SanctionsProgram;

import java.util.List;

public interface SanctionsProgramReadPlatformService {
    List<SanctionsProgram> fetchAllSanctionsProgram();

    SanctionsProgram fetchSanctionsProgramById(Integer id);
}
