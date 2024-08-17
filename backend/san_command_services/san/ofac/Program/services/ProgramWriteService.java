

package com.ponsun.san.ofac.Program.services;

import com.ponsun.san.ofac.Program.data.ProgramData;

import java.util.List;

public interface ProgramWriteService {
    List<ProgramData> fetchAllProgramData(String id);
}
