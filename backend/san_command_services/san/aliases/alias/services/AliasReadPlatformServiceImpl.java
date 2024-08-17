package com.ponsun.san.aliases.alias.services;



import com.ponsun.san.aliases.alias.domain.Alias;
import com.ponsun.san.aliases.alias.domain.AliasRepository;
import com.ponsun.san.aliases.alias.domain.AliasRepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AliasReadPlatformServiceImpl implements AliasReadPlatformService {

    private final AliasRepositoryWrapper aliasRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final AliasRepository aliasRepository;
    @Override
    public Alias fetchAliasById(Integer id){
        return this.aliasRepository.findById(id).get();
    }
    @Override
    public List<Alias> fetchAllAlias(){ return this.aliasRepository.findAll();}
}
