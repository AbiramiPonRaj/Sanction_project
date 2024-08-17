package com.ponsun.san.searchLifcycle.search.services;

import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.search.data.SearchDataValidator;
import com.ponsun.san.searchLifcycle.search.domain.Search;
import com.ponsun.san.searchLifcycle.search.domain.SearchRepository;
import com.ponsun.san.searchLifcycle.search.domain.SearchRepositoryWrapper;
import com.ponsun.san.searchLifcycle.search.request.CreateSearchRequest;
import com.ponsun.san.searchLifcycle.search.request.UpdateSearchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchWritePlatformServiceImpl implements SearchWritePlatformService {

    private final SearchRepository searchRepository;
    private final SearchRepositoryWrapper searchRepositoryWrapper;
    private final SearchDataValidator searchDataValidator;

    @Override
    @Transactional
    public Response createSearch(CreateSearchRequest createSearchRequest) {
        try {
            this.searchDataValidator.validateSaveSearchData(createSearchRequest);
             Search search = Search.create(createSearchRequest);
            search=this.searchRepository.saveAndFlush(search);
            return Response.of(search.getId());
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Response updateSearch(Integer id, UpdateSearchRequest updateSearchRequest) {
        try {
            this.searchDataValidator.validateUpdateSearchData(updateSearchRequest);
            final Search search = this.searchRepositoryWrapper.findOneWithNotFoundDetection(id);
            search.update(updateSearchRequest);
            this.searchRepository.saveAndFlush(search);
            return Response.of(Long.valueOf(search.getId()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }


    @Override
    @Transactional
    public Response blockSearch(Integer id) {
        try {
            final Search search = this.searchRepositoryWrapper.findOneWithNotFoundDetection(id);
            search.setValid(false); // Set 'valid' to 0
            search.setStatus(Status.DELETE); // Or set the appropriate status
            search.setUpdatedAt(LocalDateTime.now());
            this.searchRepository.saveAndFlush(search);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response unblockSearch(Integer id) {
        try {
            final Search search = this.searchRepositoryWrapper.findOneWithNotFoundDetection(id);
            search.setValid(true); // Set 'valid' to 1
            search.setStatus(Status.DELETE); // Or set the appropriate status
            search.setUpdatedAt(LocalDateTime.now());
            this.searchRepository.saveAndFlush(search);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

}
