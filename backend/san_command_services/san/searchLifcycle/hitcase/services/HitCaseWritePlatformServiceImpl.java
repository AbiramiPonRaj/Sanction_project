package com.ponsun.san.searchLifcycle.hitcase.services;

import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitcase.data.HitCaseDataValidator;
import com.ponsun.san.searchLifcycle.hitcase.domain.HitCaseRepositoryWapper;
import com.ponsun.san.searchLifcycle.hitcase.domain.Hitcase;
import com.ponsun.san.searchLifcycle.hitcase.domain.HitcaseRepository;
import com.ponsun.san.searchLifcycle.hitcase.request.CreateHitCaseRequest;
import com.ponsun.san.searchLifcycle.hitcase.request.UpdateHitCaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class HitCaseWritePlatformServiceImpl implements HitCaseWritePlatformService{

    private final HitcaseRepository hitcaseRepository;
    private final HitCaseRepositoryWapper hitCaseRepositoryWapper;
    private final HitCaseDataValidator hitCaseDataValidator;

    @Override
    @Transactional
    public Response CreateHitcase (CreateHitCaseRequest createHitCaseRequest){
        try{
//            this.hitCaseDataValidator.validateSaveHitCaseData(createHitCaseRequest);

            final Hitcase hitcase = Hitcase.create(createHitCaseRequest);
            this.hitcaseRepository.saveAndFlush(hitcase);
            return Response.of(hitcase.getId());
        }catch (DataIntegrityViolationException e){
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Response updateHitcase(Integer id, UpdateHitCaseRequest updateHitCaseRequest) {
        try {
//            this.hitCaseDataValidator.validateUpdateHitCaseData(updateHitCaseRequest);
            final Hitcase hitcase = this.hitCaseRepositoryWapper.findOneWithNotFoundDetection(id);
            hitcase.update(updateHitCaseRequest);
            this.hitcaseRepository.saveAndFlush(hitcase);
            return Response.of(Long.valueOf(hitcase.getId()));

        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Response blockHitcase(Integer id) {
        try {
            final Hitcase hitcase = this.hitCaseRepositoryWapper.findOneWithNotFoundDetection(id);
            hitcase.setValid(false); // Set 'valid' to 0
            hitcase.setStatus(Status.DELETE); // Or set the appropriate status
            hitcase.setUpdatedAt(LocalDateTime.now());
            this.hitcaseRepository.saveAndFlush(hitcase);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response unblockHitcase(Integer id) {
        try {
            final Hitcase hitcase = this.hitCaseRepositoryWapper.findOneWithNotFoundDetection(id);
            hitcase.setValid(true); // Set 'valid' to 1
            hitcase.setStatus(Status.DELETE); // Or set the appropriate status
            hitcase.setUpdatedAt(LocalDateTime.now());
            this.hitcaseRepository.saveAndFlush(hitcase);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

}


