package com.ponsun.san.searchLifcycle.hitrecord.domain;

import com.ponsun.san.searchLifcycle.hitrecord.request.AbstractHitRecordBaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class HitRecordRepositoryWrapper extends AbstractHitRecordBaseRequest {
    private final HitRecordRepository hitdataRepository;

    @Transactional
    public HitRecord findOneWithNotFoundDetection(final Integer id) {
        HitRecord hitRecord = this.hitdataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HitData Not Found with Id: " + id));

        return hitRecord;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
