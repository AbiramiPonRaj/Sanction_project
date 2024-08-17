package com.ponsun.san.searchLifcycle.PendingCase.services;

import com.ponsun.san.searchLifcycle.PendingCase.data.PendingCaseData;
import com.ponsun.san.searchLifcycle.PendingCase.rowmapper.PendingCaseRowmapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PendingCaseWritePlatformServiceImpl implements PendingCaseWritePlatformService{
    private final JdbcTemplate jdbcTemplate;
    private final PendingCaseRowmapper pendingCaseRowmapper;
//    @Override
//    public List<PendingCaseData> fetchAllPendingCaseData(String levelId){
//        final PendingCaseRowmapper rowmapper = new PendingCaseRowmapper();
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("levelId", levelId);
//
//        String Qry = "SELECT " + rowmapper.tableSchema();
//        String whereClause = " WHERE a.id=b.search_id AND a.id=c.search_id AND b.id=c.hitdata_id AND b.status_id=c.status_id AND c.level_id=2 AND b.status_id IN (2,3);";
//        Qry = Qry + whereClause;
//        final List<PendingCaseData> pendingCaseData = jdbcTemplate.query(Qry, pendingCaseRowmapper
//        ,new Object[]{levelId}
//        );
//        return pendingCaseData;
//    }
@Override
public List<PendingCaseData> fetchAllPendingCaseData(){
    final PendingCaseRowmapper rowmapper = new PendingCaseRowmapper();

    String Qry = "SELECT " + rowmapper.tableSchema();
    String whereClause = " WHERE b.id=d.`hit_id` AND a.id=b.searchid AND a.id=c.search_id AND b.id=c.hitdata_id AND (c.level_id=1 OR c.level_id=2) AND c.valid=1";
    Qry = Qry + whereClause;
    final List<PendingCaseData> pendingCaseData = jdbcTemplate.query(Qry, pendingCaseRowmapper
            ,new Object[]{}
    );
    return pendingCaseData;
}

    @Override
    public List<PendingCaseData> fetchByLfourPendingCase(){
        final PendingCaseRowmapper rowmapper = new PendingCaseRowmapper();

        String Qry = "SELECT " + rowmapper.tableSchema();
        String whereClause = " WHERE b.id=d.`hit_id` AND a.id=b.searchid AND a.id=c.search_id AND b.id=c.hitdata_id AND c.statusid=2 AND c.level_id=3 AND c.valid=1";
        Qry = Qry + whereClause;
        final List<PendingCaseData> pendingCaseData = jdbcTemplate.query(Qry, pendingCaseRowmapper
                ,new Object[]{}
        );
        return pendingCaseData;
    }

}

