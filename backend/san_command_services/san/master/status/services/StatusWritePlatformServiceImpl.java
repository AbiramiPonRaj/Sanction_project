package com.ponsun.san.master.status.services;

import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.master.status.domain.Status;
import com.ponsun.san.master.status.domain.StatusRepository;
import com.ponsun.san.master.status.domain.StatusRepositoryWrapper;
import com.ponsun.san.master.status.request.CreateStatusRequest;
import com.ponsun.san.master.status.request.UpdateStatusRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j

public class StatusWritePlatformServiceImpl implements StatusWritePlatformService {
    private final StatusRepository statusRepository;
    private final StatusRepositoryWrapper statusRepositoryWrapper;

    public Response createStatus(CreateStatusRequest createStatusRequest) {
        try {
            final Status status = Status.create(createStatusRequest);
            this.statusRepository.saveAndFlush(status);
            return Response.of(Long.valueOf(status.getId()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response updateStatus(Integer id, UpdateStatusRequest updateStatusRequest) {
        try {
            Status status = this.statusRepositoryWrapper.findOneWithNotFoundDetection(id);

            status.setCode(updateStatusRequest.getCode());
            status.setName(updateStatusRequest.getName());
            status.setValid(updateStatusRequest.getValid());


            status = this.statusRepository.saveAndFlush(status);

            return Response.of(Long.valueOf(status.getId()));

        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }


    @Override
    @Transactional
    public Response blockStatus(Integer id) {
        try {
            final Status status = this.statusRepositoryWrapper.findOneWithNotFoundDetection(id);
            status.setValid(false); // Set 'valid' to 0
            status.setStatus(com.ponsun.san.common.entity.Status.DELETE); // Or set the appropriate status
            status.setUpdatedAt(LocalDateTime.now());
            this.statusRepository.saveAndFlush(status);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response unblockStatus(Integer id) {
        try {
            final Status status = this.statusRepositoryWrapper.findOneWithNotFoundDetection(id);
            status.setValid(true); // Set 'valid' to 1
            status.setStatus(com.ponsun.san.common.entity.Status.DELETE); // Or set the appropriate status
            status.setUpdatedAt(LocalDateTime.now());
            this.statusRepository.saveAndFlush(status);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}

