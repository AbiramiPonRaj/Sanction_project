package com.ponsun.san.master.sanctionsProgram.services;


import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.master.sanctionsProgram.data.SanctionsProgramDataValidator;
import com.ponsun.san.master.sanctionsProgram.domain.SanctionsProgram;
import com.ponsun.san.master.sanctionsProgram.domain.SanctionsProgramRepository;
import com.ponsun.san.master.sanctionsProgram.domain.SanctionsProgramRepositoryWrapper;
import com.ponsun.san.master.sanctionsProgram.request.CreateSanctionsProgramRequest;
import com.ponsun.san.master.sanctionsProgram.request.UpdateSanctionsProgramRequest;
import com.ponsun.san.master.sanctionsProgram.rowmapper.SanctionsProgramRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SanctionsProgramWritePlatformServiceImpl implements SanctionsProgramWritePlatformService {

    private final SanctionsProgramRepository sanctionsProgramRepository;
    private final SanctionsProgramRepositoryWrapper sanctionsProgramRepositoryWrapper;
    private final SanctionsProgramDataValidator sanctionsProgramDataValidator;
    private final SanctionsProgramRowMapper sanctionsProgramRowMapper;
    private final JdbcTemplate jdbcTemplate;


    @Override
    @Transactional
    public Response createSanctionsProgram(CreateSanctionsProgramRequest createSanctionsProgramRequest) {
        try{
            this.sanctionsProgramDataValidator.validateSaveSanctionsProgramData(createSanctionsProgramRequest);
            final SanctionsProgram sanctionsProgram = SanctionsProgram.create(createSanctionsProgramRequest);
            this.sanctionsProgramRepository.saveAndFlush(sanctionsProgram);
            return Response.of(Long.valueOf(sanctionsProgram.getPrimaryKey()));
        }catch (DataIntegrityViolationException e){
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response updateSanctionsProgram(Integer id, UpdateSanctionsProgramRequest updateSanctionsProgramRequest) {
        try {
            this.sanctionsProgramDataValidator.validateUpdateSanctionsProgramData(updateSanctionsProgramRequest);
            final SanctionsProgram sanctionsProgram = this.sanctionsProgramRepositoryWrapper.findOneWithNotFoundDetection(id);
            sanctionsProgram.update(updateSanctionsProgramRequest);
            this.sanctionsProgramRepository.saveAndFlush(sanctionsProgram);
            return Response.of(Long.valueOf(sanctionsProgram.getPrimaryKey()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}
