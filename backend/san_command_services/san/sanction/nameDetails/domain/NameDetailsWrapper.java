package com.ponsun.san.sanction.nameDetails.domain;


import com.ponsun.san.sanction.nameDetails.request.AbstractNameDetailsRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NameDetailsWrapper extends AbstractNameDetailsRequest {

    private final NameDetailsRepository nameDetailsRepository;

    @Transactional
    public NameDetails findOneWithNotFoundDetection (final Integer id) {
        return this.nameDetailsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AdminConfigModule Not found " + id));
    }

    @Override
    public String toString(){ return super.toString();}
}

