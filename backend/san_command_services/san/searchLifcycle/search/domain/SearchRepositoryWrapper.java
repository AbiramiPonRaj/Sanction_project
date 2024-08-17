package com.ponsun.san.searchLifcycle.search.domain;

import com.ponsun.san.searchLifcycle.search.request.AbstractSearchBaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class SearchRepositoryWrapper extends AbstractSearchBaseRequest {

    private final SearchRepository searchRepository;

    @Transactional
    public Search findOneWithNotFoundDetection(final Integer id){
        return  this.searchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("sanSearch Not found " + id));
    }
    @Override
    public String toString(){ return super.toString();}

}

