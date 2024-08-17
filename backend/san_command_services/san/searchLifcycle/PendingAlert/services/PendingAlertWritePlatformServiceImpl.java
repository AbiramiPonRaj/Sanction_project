package com.ponsun.san.searchLifcycle.PendingAlert.services;


import com.ponsun.san.searchLifcycle.PendingAlert.data.PendingAlertData;
import com.ponsun.san.searchLifcycle.PendingAlert.rowmapper.PendingAlertRowmapper;
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
public class PendingAlertWritePlatformServiceImpl implements PendingAlertWritePlatformService{
    private final JdbcTemplate jdbcTemplate;
    private final PendingAlertRowmapper pendingAlertRowmapper;

    @Override
    public List<PendingAlertData>fetchAllPendingAlertData(String levelId){
        final PendingAlertRowmapper rowmapper = new PendingAlertRowmapper();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("levelId", levelId);

        String Qry = "SELECT " + rowmapper.tableSchema();
        String whereClause = " WHERE a.id=b.searchid AND a.id=c.search_id AND b.id=c.hitdata_id and c.statusid=1 AND c.level_id=? AND c.valid=1";
        Qry = Qry + whereClause;
        final List<PendingAlertData> pendingAlertData = jdbcTemplate.query(Qry, pendingAlertRowmapper
                ,new Object[]{levelId}
        );
        return pendingAlertData;
    }


}
