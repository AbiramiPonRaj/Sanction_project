package com.ponsun.san.searchLifcycle.hitcaselifecycle.domain;

import com.ponsun.san.searchLifcycle.hitcaselifecycle.request.AbstractHitCaseLifeCycleRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HitCaseLifeCycleRepositoryWrapper extends AbstractHitCaseLifeCycleRequest {
    private final HitcaseLifecycleRepository hitcaseLifecycleRepository;

    @Transactional
    public HitcaseLifecycle findOneWithNotFoundDetection(final Integer id){
        return (HitcaseLifecycle) this.hitcaseLifecycleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("HitcaseLifeCucle Not found " + id));

    }
    @Override
    public String toString(){return super.toString();

    }

}
