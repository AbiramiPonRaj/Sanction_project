package com.ponsun.san.aliases.alias.services;
import com.ponsun.san.aliases.alias.data.AliasDataValidator;
import com.ponsun.san.aliases.alias.domain.Alias;
import com.ponsun.san.aliases.alias.domain.AliasRepository;
import com.ponsun.san.aliases.alias.domain.AliasRepositoryWrapper;
import com.ponsun.san.aliases.alias.request.CreateAliasRequest;
import com.ponsun.san.aliases.alias.request.UpdateAliasRequest;
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
public class AliasWritePlatformServiceImpl implements AliasWritePlatformService {

    private final AliasRepositoryWrapper aliasRepositoryWrapper;
    private final AliasDataValidator aliasDataValidator;
    private final JdbcTemplate jdbcTemplate;
    private final AliasRepository aliasRepository;

    @Override
    @Transactional
    public Response createAlias(CreateAliasRequest createAliasRequest) {
        try{
            this.aliasDataValidator.validateSaveAliasData(createAliasRequest);
            final Alias alias = Alias.create(createAliasRequest);
            this.aliasRepository.saveAndFlush(alias);
            return Response.of(Long.valueOf(alias.getPrimaryKey()));
        }catch (DataIntegrityViolationException e){
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response updateAlias(Integer id, UpdateAliasRequest updateAliasRequest) {
        try {
            this.aliasDataValidator.validateUpdateAliasData(updateAliasRequest);
            final Alias alias = this.aliasRepositoryWrapper.findOneWithNotFoundDetection(id);
            alias.update(updateAliasRequest);
            this.aliasRepository.saveAndFlush(alias);
            return Response.of(Long.valueOf(alias.getPrimaryKey()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}
