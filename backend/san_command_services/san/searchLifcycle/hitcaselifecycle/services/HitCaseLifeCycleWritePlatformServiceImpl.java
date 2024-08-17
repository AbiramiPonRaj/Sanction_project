package com.ponsun.san.searchLifcycle.hitcaselifecycle.services;

import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.data.HitCaselifeCycleDataValidator;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.domain.HitCaseLifeCycleRepositoryWrapper;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.domain.HitcaseLifecycle;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.domain.HitcaseLifecycleRepository;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.request.CreateHitCaseLifeCycleRequest;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.request.UpdateHitCaseLifeCycleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class HitCaseLifeCycleWritePlatformServiceImpl implements HitCaseLifeCycleWritePlatformService {
    private final HitcaseLifecycleRepository hitcaseLifecycleRepository;

    private final HitCaseLifeCycleRepositoryWrapper hitCaseLifeCycleRepositoryWrapper;
    private final HitCaselifeCycleDataValidator hitCaselifeCycleDataValidator;


//    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Response createHitCaseLifeCycle (CreateHitCaseLifeCycleRequest createHitCaseLifeCycleRequest) {
        try {
            this.hitCaselifeCycleDataValidator.validateSaveHitCaselifeCycleData(createHitCaseLifeCycleRequest);

            final HitcaseLifecycle hitcaseLifecycle = HitcaseLifecycle.create(createHitCaseLifeCycleRequest);//entity
            this.hitcaseLifecycleRepository.saveAndFlush(hitcaseLifecycle);
            return Response.of(Long.valueOf(hitcaseLifecycle.getCaseId()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }




    @Override
    @Transactional
    public Response updateHitCaseLifeCycle(Integer id, UpdateHitCaseLifeCycleRequest updateHitCaseLifeCycleRequest) {
        try {
            this.hitCaselifeCycleDataValidator.validateUpdateHitCaselifeCycleData(updateHitCaseLifeCycleRequest);
            final HitcaseLifecycle hitcaseLifecycle = this.hitCaseLifeCycleRepositoryWrapper.findOneWithNotFoundDetection(id);
            hitcaseLifecycle.update(updateHitCaseLifeCycleRequest);
            this.hitcaseLifecycleRepository.saveAndFlush(hitcaseLifecycle);
            return Response.of(Long.valueOf(hitcaseLifecycle.getId()));

        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Response blockHitCaseLifeCycle(Integer id) {
        try {
            final HitcaseLifecycle hitcaseLifecycle = this.hitCaseLifeCycleRepositoryWrapper.findOneWithNotFoundDetection(id);
            hitcaseLifecycle.setValid(false); // Set 'valid' to 0
            hitcaseLifecycle.setStatus(Status.DELETE); // Or set the appropriate status
            hitcaseLifecycle.setUpdatedAt(LocalDateTime.now());
            this.hitcaseLifecycleRepository.saveAndFlush(hitcaseLifecycle);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response unblockHitCaseLifeCycle(Integer id) {
        try {
            final HitcaseLifecycle hitcaseLifecycle = this.hitCaseLifeCycleRepositoryWrapper.findOneWithNotFoundDetection(id);
            hitcaseLifecycle.setValid(true); // Set 'valid' to 1
            hitcaseLifecycle.setStatus(Status.DELETE); // Or set the appropriate status
            hitcaseLifecycle.setUpdatedAt(LocalDateTime.now());
            this.hitcaseLifecycleRepository.saveAndFlush(hitcaseLifecycle);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}

