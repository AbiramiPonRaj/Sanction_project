package com.ponsun.san.searchLifcycle.hitrecord.services;


import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitrecord.data.HitRecordValidator;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecord;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecordRepository;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecordRepositoryWrapper;
import com.ponsun.san.searchLifcycle.hitrecord.request.CreateHitRecordRequest;
import com.ponsun.san.searchLifcycle.hitrecord.request.UpdateHitRecordRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class HitRecordWritePlatformServiceImpl implements HitRecordWritePlatformService {
    private final HitRecordRepository hitdataRepository;
    private final HitRecordRepositoryWrapper hitRecordRepositoryWrapper;
    private final HitRecordValidator hitRecordValidator;

    @Override
    @Transactional
    public Response createHitData(CreateHitRecordRequest createHitDataRequest) {
        try {
            this.hitRecordValidator.validateSaveHitDataData(createHitDataRequest);

            final HitRecord hitRecord = HitRecord.create(createHitDataRequest);
            this.hitdataRepository.saveAndFlush(hitRecord);
            return Response.of(hitRecord.getId());
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response updateHitdata(Integer id, UpdateHitRecordRequest updateHitDataRequest) {
        try {
            this.hitRecordValidator.validateUpdateHitDataData(updateHitDataRequest);
            final HitRecord hitRecord = this.hitRecordRepositoryWrapper.findOneWithNotFoundDetection(id);
            hitRecord.update(updateHitDataRequest);
            this.hitdataRepository.saveAndFlush(hitRecord);

            return Response.of(Long.valueOf(hitRecord.getId()));

        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response blockHitData(Integer id) {
        try {
            final HitRecord hitRecord = this.hitRecordRepositoryWrapper.findOneWithNotFoundDetection(id);
            hitRecord.setValid(false); // Set 'valid' to 0
            //hitData.setStatus(Status.ACTIVE); // Or set the appropriate status
            //hitData.onUpdate();
            this.hitdataRepository.saveAndFlush(hitRecord);
            return Response.of(id);
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response unblockHitData(Integer id) {
        try {
            final HitRecord hitRecord = this.hitRecordRepositoryWrapper.findOneWithNotFoundDetection(id);
            hitRecord.setValid(true); // Set 'valid' to 1
            hitRecord.setStatus(Status.ACTIVE); // Or set the appropriate status
            hitRecord.setUpdatedAt(LocalDateTime.now());
            this.hitdataRepository.saveAndFlush(hitRecord);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}
