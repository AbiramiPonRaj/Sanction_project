package com.ponsun.san.searchLifcycle.hitrecordtable.services;

import com.ponsun.san.searchLifcycle.hitrecordtable.data.HitRecordDataTableData;
import com.ponsun.san.searchLifcycle.hitrecordtable.rowmapper.HitRecordTableRowmapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor
public class HitRecordTableWritePlatformServiceImpl implements HitRecordTableWritePlatformService {
    private final JdbcTemplate jdbcTemplate;
    private final HitRecordTableRowmapper hitRecordTableRowmapper;

//    @Override
//    public List<HitDataTableData> fetchAllHitDataTableData(){
//        final HitDataTableRowmapper rowmapper = new HitDataTableRowmapper();
//
//        String Qry = "SELECT " + rowmapper.tableSchema();
//        String whereClause = " WHERE a.id=b.search_id AND b.criminal_id=c.id AND a.country_id=d.id AND a.state_id=e.id AND b.valid=1 AND a.level_id=?";
//        Qry = Qry + whereClause;
//        final List<HitDataTableData> hitDataTableData = jdbcTemplate.query(Qry, hitDataTableRowmapper
//
//        );
//        return hitDataTableData;
//    }
    @Override
    public List<HitRecordDataTableData> fetchAllHitDataTableData(String levelId){
        final HitRecordTableRowmapper rowmapper = new HitRecordTableRowmapper();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("levelId", levelId);

        String Qry = "SELECT " + rowmapper.tableSchema();
        String whereClause = " WHERE a.id=b.search_id AND b.criminal_id=c.id AND a.country_id=d.id AND a.state_id=e.id AND b.valid=1 AND a.level_id=?";
        Qry = Qry + whereClause;

        final List<HitRecordDataTableData> hitRecordDataTableData = jdbcTemplate.query(Qry, hitRecordTableRowmapper,
                new Object[]{levelId}
        );
        return hitRecordDataTableData;
    }

}
