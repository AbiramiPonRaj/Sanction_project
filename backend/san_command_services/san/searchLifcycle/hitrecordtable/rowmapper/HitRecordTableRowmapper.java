package com.ponsun.san.searchLifcycle.hitrecordtable.rowmapper;

import com.ponsun.san.searchLifcycle.hitrecordtable.data.HitRecordDataTableData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class HitRecordTableRowmapper implements RowMapper<HitRecordDataTableData> {
//    SELECT a.id,b.`criminal_id`,b.name,b.`matching_score`,d.name,e.`name`,c.`dob`
//    FROM `crm_search` a,crm_hitdata b,`crm_criminal_name` c,`crm_config_country` d,`crm_config_state` e
//    WHERE a.id=b.`search_id` AND b.`criminal_id`=c.id AND a.`country_id`=d.id AND a.`state_id`=e.id AND a.`level_id`=1;

//    SELECT a.id AS searchId,b.criminal_id AS criminalId,b.name AS criminalName,
//
//    b.matching_score AS MatchScore,d.name AS Country,e.name AS State,c.dob AS dob
//
//    FROM crm_search a,crm_hitdata b,crm_criminal_name c,crm_config_country d,crm_config_state e
//
//    WHERE a.id=b.search_id AND b.criminal_id=c.id AND a.country_id=d.id AND a.state_id=e.id AND a.level_id=1;

    public final String schema;

    public HitRecordTableRowmapper() {

        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM search a,hitrecord b,crm_criminal_name c,country d,san_config_state e");
        this.schema = builder.toString();
    }

    public String schema() {
        return this.schema;
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.id AS searchId,b.criminal_id AS criminalId,b.id as hitId,b.name AS criminalName,b.matching_score AS MatchScore,d.name AS Country,e.name AS State,c.dob AS dob");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public HitRecordDataTableData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer searchId = rs.getInt("searchId");
        final Integer criminalId = rs.getInt("criminalId");
        final Integer hitId = rs.getInt("hitId");
        final String criminalName = rs.getString("criminalName");
        final Double MatchScore = rs.getDouble("MatchScore");
        final String Country = rs.getString("Country");
        final String State = rs.getString("State");
        final String dob = rs.getString("dob");
        final String levelId = "";
        return HitRecordDataTableData.newInstance(searchId,criminalId,hitId,criminalName,MatchScore,Country,State,dob,levelId);
    }
}

