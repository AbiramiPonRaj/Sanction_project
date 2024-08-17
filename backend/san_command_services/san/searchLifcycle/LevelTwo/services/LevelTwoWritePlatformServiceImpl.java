package com.ponsun.san.searchLifcycle.LevelTwo.services;

import com.ponsun.san.searchLifcycle.LevelTwo.data.LevelTwoData;
import com.ponsun.san.searchLifcycle.LevelTwo.rowmapper.LevelTwoRowmapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LevelTwoWritePlatformServiceImpl implements LevelTwoWritePlatformService{
    private final JdbcTemplate jdbcTemplate;
    private final LevelTwoRowmapper levelTwoRowmapper;

    @Override
    public List<LevelTwoData> fetchAllLevelTwoData(){
        final LevelTwoRowmapper rowmapper = new LevelTwoRowmapper();
        String Qry = "SELECT " + rowmapper.tableSchema();
        String whereClause = " WHERE f.hitdata_id=b.id AND f.valid=1 AND f.status_id=2 AND a.id=b.search_id AND b.criminal_id=c.id AND a.country_id=d.id AND a.state_id=e.id";
        Qry = Qry + whereClause;
        final List<LevelTwoData> levelTwoData = jdbcTemplate.query(Qry, levelTwoRowmapper);
        return levelTwoData;
    }

}
