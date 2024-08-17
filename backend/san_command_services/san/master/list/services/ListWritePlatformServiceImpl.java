package com.ponsun.san.master.list.services;

import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;

import com.ponsun.san.master.country.domain.Country;
import com.ponsun.san.master.list.data.ListDataValidator;
import com.ponsun.san.master.list.domain.ListRepository;
import com.ponsun.san.master.list.domain.ListRepositoryWrapper;
import com.ponsun.san.master.list.domain.Lists;
import com.ponsun.san.master.list.request.CreateListRequest;
import com.ponsun.san.master.list.request.UpdateListRequest;
import com.ponsun.san.master.list.rowmapper.ListRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListWritePlatformServiceImpl implements ListWritePlatformService {

    private final ListRepository listRepository;
    private final ListRepositoryWrapper listRepositoryWrapper;
    private final ListDataValidator listDataValidator;
    private final ListRowMapper listRowMapper;
    private final JdbcTemplate jdbcTemplate;
    @Override
    @Transactional
    public Response createList(CreateListRequest createListRequest) {
        try{
            this.listDataValidator.validateSaveListData(createListRequest);
            final Lists list = Lists.create(createListRequest);
            this.listRepository.saveAndFlush(list);
            return Response.of(Long.valueOf(list.getPrimaryKey()));
        }catch (DataIntegrityViolationException e){
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response updateList(Integer id, UpdateListRequest updateListRequest) {
        try {
            this.listDataValidator.validateUpdateListData(updateListRequest);
            final Lists list = this.listRepositoryWrapper.findOneWithNotFoundDetection(id);
            list.update(updateListRequest);
            this.listRepository.saveAndFlush(list);
            return Response.of(Long.valueOf(list.getPrimaryKey()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}
