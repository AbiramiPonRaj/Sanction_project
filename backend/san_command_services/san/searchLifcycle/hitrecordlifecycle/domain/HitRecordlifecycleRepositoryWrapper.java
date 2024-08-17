package com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain;

import com.ponsun.san.searchLifcycle.hitrecordlifecycle.request.AbstracthitrecordlifecycleBaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HitRecordlifecycleRepositoryWrapper extends AbstracthitrecordlifecycleBaseRequest {
private final HitRecordlifecycleRepository hitRecordlifecycleRepository;

    @Transactional
    public HitRecordLifecycle findOneWithNotFoundDetection(final Integer id) {
        return this.hitRecordlifecycleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hitdatalifecycle Not found " + id));
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
