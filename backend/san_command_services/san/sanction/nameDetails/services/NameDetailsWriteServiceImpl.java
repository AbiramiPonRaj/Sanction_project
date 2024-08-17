package com.ponsun.san.sanction.nameDetails.services;


import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.sanction.nameDetails.domain.NameDetails;
import com.ponsun.san.sanction.nameDetails.domain.NameDetailsRepository;
import com.ponsun.san.sanction.nameDetails.domain.NameDetailsWrapper;
import com.ponsun.san.sanction.nameDetails.request.CreateNameDetailsRequest;
import com.ponsun.san.sanction.nameDetails.request.UpdateNameDetailsRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NameDetailsWriteServiceImpl implements NameDetailsWriteService {
    private final NameDetailsRepository nameDetailsRepository;
    private final NameDetailsWrapper nameDetailsWrapper;
    @Override
    @Transactional
    public Response createNameDetails(CreateNameDetailsRequest createNameDetailsRequest) {
        try {
            //this.NameDetailsDataValidator.validateSaveNameDetails(createNameDetailsRequest);
             NameDetails nameDetails = NameDetails.create(createNameDetailsRequest);
            nameDetails = nameDetailsRepository.saveAndFlush(nameDetails);
            return Response.of(Long.valueOf(nameDetails.getId()));

        } catch (DataIntegrityViolationException e) {
            log.error("Error creating NameDetails: {}", e.getMessage(), e);
            throw new EQAS_SAN_ApplicationException("Error creating NameDetails: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response updateNameDetails(Integer id, UpdateNameDetailsRequest updateNameDetailsRequest) {
        try {
            //this.NameDetailsDataValidator.validateUpdateNameDetails(updateNameDetailsRequest);
            final NameDetails nameDetails = this.nameDetailsWrapper.findOneWithNotFoundDetection(id);
            nameDetails.update(updateNameDetailsRequest);
            this.nameDetailsRepository.saveAndFlush(nameDetails);
            return Response.of(Long.valueOf(nameDetails.getId()));

        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response deActivate(Integer id, Integer euid) {
        try {
            NameDetails nameDetails = this.nameDetailsWrapper.findOneWithNotFoundDetection(id);
            nameDetails.setStatus(Status.DELETE);
            nameDetails.setUpdatedAt(LocalDateTime.now());
            this.nameDetailsRepository.save(nameDetails);
            return Response.of(nameDetails.getId());
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}
