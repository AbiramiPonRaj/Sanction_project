package com.ponsun.san.searchLifcycle.hitrecordlifecycle.services;

import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.data.HitrecorddatalifecycleDataValidator;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain.HitRecordLifecycle;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain.HitRecordlifecycleRepository;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain.HitRecordlifecycleRepositoryWrapper;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.request.CreateHitRecordLifecycleRequest;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.request.UpdateHitdataLifecycleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class HitRecordLifecycleWritePlatformServiceImpl implements HitRecordLifecycleWritePlatformService {
    private final HitRecordlifecycleRepository hitRecordlifecycleRepository;
    private final HitRecordlifecycleRepositoryWrapper hitRecordlifecycleRepositoryWrapper;
    private final HitrecorddatalifecycleDataValidator hitrecorddatalifecycleDataValidator;
    //private final InsertWritePlatformService insertWritePlatformService;

    @Override
    @Transactional
    public Response createHitdataLifecycle(CreateHitRecordLifecycleRequest createHitRecordLifecycleRequest) {
        try {
            this.hitrecorddatalifecycleDataValidator.validateSaveHitDatalifecycleData(createHitRecordLifecycleRequest);
            //update is valid in hitdata
            //System.out.println("AA"+createHitdataLifecycle.getHitdataId());
            //this.hitDataWritePlatformService.blockHitData(createHitdataLifecycle.getHitdataId());
            //updateValidFlag(pendingCaseData.getSearchId(),pendingCaseData.getCriminalId(),pendingCaseData.getHitdataId());
            hitRecordlifecycleRepository.updateValidBySearchIdAndCriminalId(createHitRecordLifecycleRequest.getSearchId(), createHitRecordLifecycleRequest.getCriminalId(),false);
            final HitRecordLifecycle hitRecordLifecycle = HitRecordLifecycle.create(createHitRecordLifecycleRequest);
            this.hitRecordlifecycleRepository.saveAndFlush(hitRecordLifecycle);

            return Response.of(Long.valueOf(hitRecordLifecycle.getId()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
//    @Override
//    @Transactional
//    public Response l2_createHitdataLifecycle(CreateHitdataLifecycle createHitdataLifecycle) {
//        try {
//            //this.hitdatalifecycleDataValidator.validateSaveHitDatalifecycleData(createHitdataLifecycle);
//            //update is valid in hitdatalifecycle
//            updateValidFlag(createHitdataLifecycle.getSearchId(),createHitdataLifecycle.getCriminalId());
//            //update is valid in hitdata
//            this.hitDataWritePlatformService.blockHitData(createHitdataLifecycle.getHitdataId());
//
//            final HitdataLifecycle hitdataLifecycle = HitdataLifecycle.create(createHitdataLifecycle);
//            this.hitdatalifecycleRepository.saveAndFlush(hitdataLifecycle);
//            return Response.of(Long.valueOf(hitdataLifecycle.getId()));
//
//        } catch (DataIntegrityViolationException e) {
//            throw new EQAS_SAN_ApplicationException(e.getMessage());
//        }
//    }
    @Override
    @Transactional
    public Response updateHitdataLifecycle(Integer id, UpdateHitdataLifecycleRequest updateHitdataLifecycleRequest) {
        try {
            this.hitrecorddatalifecycleDataValidator.validateUpdateHitDataData(updateHitdataLifecycleRequest);

            final HitRecordLifecycle hitRecordLifecycle = this.hitRecordlifecycleRepositoryWrapper.findOneWithNotFoundDetection(id);
            hitRecordLifecycle.update(updateHitdataLifecycleRequest);
            this.hitRecordlifecycleRepository.saveAndFlush(hitRecordLifecycle);

            return Response.of(Long.valueOf(hitRecordLifecycle.getId()));

        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Response blockHitdataLifecycle(Integer id) {
        try {
            final HitRecordLifecycle hitRecordLifecycle = this.hitRecordlifecycleRepositoryWrapper.findOneWithNotFoundDetection(id);
            hitRecordLifecycle.setValid(false); // Set 'valid' to 0
            hitRecordLifecycle.setStatus(Status.DELETE); // Or set the appropriate status
            hitRecordLifecycle.setUpdatedAt(LocalDateTime.now());
            this.hitRecordlifecycleRepository.saveAndFlush(hitRecordLifecycle);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response unblockHitdataLifecycle(Integer id) {
        try {
            final HitRecordLifecycle hitRecordLifecycle = this.hitRecordlifecycleRepositoryWrapper.findOneWithNotFoundDetection(id);
            hitRecordLifecycle.setValid(true); // Set 'valid' to 1
            hitRecordLifecycle.setStatus(Status.DELETE); // Or set the appropriate status
            hitRecordLifecycle.setUpdatedAt(LocalDateTime.now());
            this.hitRecordlifecycleRepository.saveAndFlush(hitRecordLifecycle);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

//    @Transactional
//    public void updateValidFlag(Integer searchId, Integer criminalId) {
//        boolean valid = false; // Set the value you want to update (0 in this case)
//        hitdatalifecycleRepository.updateValidBySearchIdAndCriminalId(searchId, criminalId,valid);
//    }
}