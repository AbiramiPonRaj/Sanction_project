package com.ponsun.san.searchLifcycle.hitrecord.services;

import com.ponsun.san.searchLifcycle.hitrecord.data.HitRecordData;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecord;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecordRepository;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecordRepositoryWrapper;
import com.ponsun.san.searchLifcycle.hitrecord.rowmapper.HitRecordRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class HitRecordReadPlatformServiceImpl implements HitRecordReadPlatformService {
    private final HitRecordRepositoryWrapper hitRecordRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final HitRecordRepository hitdataRepository;
    private final HitRecordRowMapper hitRecordRowMapper;

    @Override
    public HitRecord fetchAHitDataById(Integer id) {
        return this.hitdataRepository.findById(id).get();
    }

    @Override
    public List<HitRecordData> fetchAllHitDataById(Integer searchId) {

        final HitRecordRowMapper rowMapper = new HitRecordRowMapper();
        String Qry = "SELECT "  + rowMapper.tableSchema();
        String whereClause = " WHERE searchId=? ";
        Qry = Qry + whereClause;
        final List<HitRecordData> hitRecordDataList=  jdbcTemplate.query(Qry, new Object[]{searchId}, hitRecordRowMapper);

        //List<HitRecordData> hitRecordList= (List<HitRecordData>) this.hitdataRepository.findById(id).get();
        return hitRecordDataList;
    }

    @Override
    public List<HitRecord> fetchAll() {return this.hitdataRepository.findAll();
    }
}
