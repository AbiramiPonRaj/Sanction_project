package com.ponsun.san.aliases.aliasType.services;


import com.ponsun.san.aliases.aliasType.domain.AliasType;
import com.ponsun.san.aliases.aliasType.domain.AliasTypeRepository;
import com.ponsun.san.aliases.aliasType.domain.AliasTypeRepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AliasTypeReadPlatformServiceImpl implements AliasTypeReadPlatformService {

    private final AliasTypeRepositoryWrapper aliasTypeRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final AliasTypeRepository aliasTypeRepository;
    @Override
    public AliasType fetchAliasTypeById(Integer id){
        return this.aliasTypeRepository.findById(id).get();
    }
    @Override
    public List<AliasType> fetchAllAliasType(){ return this.aliasTypeRepository.findAll();}
}
