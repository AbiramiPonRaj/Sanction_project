package com.ponsun.san.master.sanctionsProgram.services;



import com.ponsun.san.master.sanctionsProgram.domain.SanctionsProgram;
import com.ponsun.san.master.sanctionsProgram.domain.SanctionsProgramRepository;
import com.ponsun.san.master.sanctionsProgram.domain.SanctionsProgramRepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SanctionsProgramReadPlatformServiceImpl implements SanctionsProgramReadPlatformService {

    private final SanctionsProgramRepositoryWrapper sanctionsProgramRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final SanctionsProgramRepository sanctionsProgramRepository;
    @Override
    public SanctionsProgram fetchSanctionsProgramById(Integer id){
        return this.sanctionsProgramRepository.findById(id).get();
    }
    @Override
    public List<SanctionsProgram> fetchAllSanctionsProgram(){ return this.sanctionsProgramRepository.findAll();}
}
