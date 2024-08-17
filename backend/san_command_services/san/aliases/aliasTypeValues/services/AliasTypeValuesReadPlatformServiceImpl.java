package com.ponsun.san.aliases.aliasTypeValues.services;


import com.ponsun.san.aliases.aliasTypeValues.domain.AliasTypeValues;
import com.ponsun.san.aliases.aliasTypeValues.domain.AliasTypeValuesRepository;
import com.ponsun.san.aliases.aliasTypeValues.domain.AliasTypeValuesRepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AliasTypeValuesReadPlatformServiceImpl implements AliasTypeValuesReadPlatformService {

    private final AliasTypeValuesRepositoryWrapper aliasTypeValuesRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final AliasTypeValuesRepository aliasTypeValuesRepository;
    @Override
    public AliasTypeValues fetchAliasTypeValuesById(Integer id){
        return this.aliasTypeValuesRepository.findById(id).get();
    }
    @Override
    public List<AliasTypeValues> fetchAllAliasTypeValues(){ return this.aliasTypeValuesRepository.findAll();}
}
