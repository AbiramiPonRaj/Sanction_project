package com.ponsun.san.searchLifcycle.PendingAlert.rowmapper;

import com.ponsun.san.searchLifcycle.PendingAlert.data.PendingAlertData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class PendingAlertRowmapper implements RowMapper<PendingAlertData> {
//    SELECT a.id,b.id,b.criminal_id,b.name,b.matching_score,c.remark FROM crm_search a, crm_hitdata b,`crm_hitdata_lifecycle` c
//    WHERE a.id=b.search_id AND a.id=c.search_id AND b.id=c.hitdata_id AND b.status_id=c.status_id AND c.level_id=2 AND b.status_id IN (2,3);

    public final String schema;
    public PendingAlertRowmapper(){

        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM search a,hitrecord b, hitrecord_lifecycle c");
        this.schema = builder.toString();
    }

    public String schema()
    {return this.schema;
    }

    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.id AS searchId, b.id AS hitId, b.criminalid AS criminalId, b.name AS criminalName, b.matchingscore AS matchingScore,c.remark AS remark,c.created_At AS created_at ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public PendingAlertData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer searchId = rs.getInt("searchId");
        final Integer hitId = rs.getInt("hitId");
        final Integer criminalId = rs.getInt("criminalId");
        final String criminalName = rs.getString("criminalName");
        final Integer matchingScore = rs.getInt("matchingScore");
        final String remark = rs.getString("remark");
        final String createdAt = rs.getString("created_at");
        return PendingAlertData.newInstance(searchId, hitId, criminalId, criminalName, matchingScore, remark,createdAt);
    }


}
