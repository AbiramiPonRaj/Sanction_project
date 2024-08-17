package com.ponsun.san.master.list.services;




import com.ponsun.san.master.list.domain.ListRepository;
import com.ponsun.san.master.list.domain.ListRepositoryWrapper;
import com.ponsun.san.master.list.domain.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListReadPlatformServiceImpl implements ListReadPlatformService {

    private final ListRepositoryWrapper listRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final ListRepository listRepository;
    @Override
    public Lists fetchListById(Integer id){
        return this.listRepository.findById(id).get();
    }
    @Override
    public List<Lists> fetchAllList(){ return this.listRepository.findAll();}
}
