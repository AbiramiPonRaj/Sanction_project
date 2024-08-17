package com.ponsun.san.aliases.aliasType.services;

import com.ponsun.san.aliases.aliasType.data.AliasTypeDataValidator;
import com.ponsun.san.aliases.aliasType.domain.AliasType;
import com.ponsun.san.aliases.aliasType.domain.AliasTypeRepository;
import com.ponsun.san.aliases.aliasType.domain.AliasTypeRepositoryWrapper;
import com.ponsun.san.aliases.aliasType.request.CreateAliasTypeRequest;
import com.ponsun.san.aliases.aliasType.request.UpdateAliasTypeRequest;
import com.ponsun.san.aliases.aliasType.rowmapper.AliasTypeRowMapper;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AliasTypeWritePlatformServiceImpl implements AliasTypeWritePlatformService {
    private final AliasTypeRepository aliasTypeRepository;
    private final AliasTypeRepositoryWrapper aliasTypeRepositoryWrapper;
    private final AliasTypeDataValidator aliasTypeDataValidator;
    private final AliasTypeRowMapper aliasTypeRowMapper;
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Response createAliasType(CreateAliasTypeRequest createAliasTypeRequest) {
        try{
            this.aliasTypeDataValidator.validateSaveAliasTypeData(createAliasTypeRequest);
            final AliasType aliasType = AliasType.create(createAliasTypeRequest);
            this.aliasTypeRepository.saveAndFlush(aliasType);
            return Response.of(Long.valueOf(aliasType.getPrimaryKey()));
        }catch (DataIntegrityViolationException e){
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    public Response updateAliasType(Integer id, UpdateAliasTypeRequest updateAliasTypeRequest) {
        try {
            this.aliasTypeDataValidator.validateUpdateAliasTypeData(updateAliasTypeRequest);
        final AliasType aliasType = this.aliasTypeRepositoryWrapper.findOneWithNotFoundDetection(id);
            aliasType.update(updateAliasTypeRequest);
            this.aliasTypeRepository.saveAndFlush(aliasType);
            return Response.of(Long.valueOf(aliasType.getPrimaryKey()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}
