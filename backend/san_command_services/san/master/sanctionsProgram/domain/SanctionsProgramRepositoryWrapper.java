package com.ponsun.san.master.sanctionsProgram.domain;

import com.ponsun.san.master.sanctionsProgram.request.AbstractSanctionsProgramRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SanctionsProgramRepositoryWrapper extends AbstractSanctionsProgramRequest {
    private final SanctionsProgramRepository sanctionsProgramRepository;


    @Transactional
    public SanctionsProgram findOneWithNotFoundDetection(final Integer id){
        return this.sanctionsProgramRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("SanctionsProgram Not found" + id));
    }

    @Override
    public String toString(){ return super.toString();}
}
