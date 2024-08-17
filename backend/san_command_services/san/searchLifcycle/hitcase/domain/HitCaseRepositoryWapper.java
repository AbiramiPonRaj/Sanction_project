package com.ponsun.san.searchLifcycle.hitcase.domain;

import com.ponsun.san.searchLifcycle.hitcase.request.AbstractHitCaseBaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HitCaseRepositoryWapper extends AbstractHitCaseBaseRequest {

    private final HitcaseRepository hitcaseRepository;


    @Transactional
    public Hitcase findOneWithNotFoundDetection(final Integer id){
        return this.hitcaseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("hitcase Not found " + id));
    }
    @Override
    public  String toString(){
        return super.toString();
    }
}
