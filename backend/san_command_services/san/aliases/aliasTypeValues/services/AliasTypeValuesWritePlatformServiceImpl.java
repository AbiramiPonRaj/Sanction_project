package com.ponsun.san.aliases.aliasTypeValues.services;


import com.ponsun.san.aliases.aliasTypeValues.data.AliasTypeValuesDataValidator;
import com.ponsun.san.aliases.aliasTypeValues.domain.AliasTypeValues;
import com.ponsun.san.aliases.aliasTypeValues.domain.AliasTypeValuesRepository;
import com.ponsun.san.aliases.aliasTypeValues.domain.AliasTypeValuesRepositoryWrapper;
import com.ponsun.san.aliases.aliasTypeValues.request.CreateAliasTypeValuesRequest;
import com.ponsun.san.aliases.aliasTypeValues.request.UpdateAliasTypeValuesRequest;
import com.ponsun.san.aliases.aliasTypeValues.rowmapper.AliasTypeValuesRowMapper;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AliasTypeValuesWritePlatformServiceImpl implements  AliasTypeValuesWritePlatformService {
    private final AliasTypeValuesRepository aliasTypeValuesRepository;
    private final AliasTypeValuesRepositoryWrapper aliasTypeValuesRepositoryWrapper;
    private final AliasTypeValuesDataValidator aliasTypeValuesDataValidator;
    private final AliasTypeValuesRowMapper aliasTypeValuesRowMapper;
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Response createAliasTypeValues(CreateAliasTypeValuesRequest createAliasTypeValuesRequest) {
        try{
            this.aliasTypeValuesDataValidator.validateSaveAliasTypeValuesData(createAliasTypeValuesRequest);
            final AliasTypeValues aliasTypeValues = AliasTypeValues.create(createAliasTypeValuesRequest);
            this.aliasTypeValuesRepository.saveAndFlush(aliasTypeValues);
            return Response.of(Long.valueOf(aliasTypeValues.getPrimaryKey()));
        }catch (DataIntegrityViolationException e){
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response updateAliasTypeValues(Integer id, UpdateAliasTypeValuesRequest updateAliasTypeValuesRequest) {
        try {
            this.aliasTypeValuesDataValidator.validateUpdateAliasTypeValuesData(updateAliasTypeValuesRequest);
            final AliasTypeValues aliasTypeValues = this.aliasTypeValuesRepositoryWrapper.findOneWithNotFoundDetection(id);
            aliasTypeValues.update(updateAliasTypeValuesRequest);
            this.aliasTypeValuesRepository.saveAndFlush(aliasTypeValues);
            return Response.of(Long.valueOf(aliasTypeValues.getPrimaryKey()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}
