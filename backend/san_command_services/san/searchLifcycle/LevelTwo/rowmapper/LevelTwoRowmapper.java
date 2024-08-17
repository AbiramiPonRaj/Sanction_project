package com.ponsun.san.searchLifcycle.LevelTwo.rowmapper;

import com.ponsun.san.searchLifcycle.LevelTwo.data.LevelTwoData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


@Data
@Service
@Slf4j
public class LevelTwoRowmapper implements RowMapper<LevelTwoData> {
    public final String schema;

    public LevelTwoRowmapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM search a,hitrecord b,crm_criminal_name c,country d,san_config_state e,hitrecord_lifecycle f");
        this.schema = builder.toString();
    }
    public String schema(){
        return this.schema;
    }
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.id AS searchId,b.criminal_id AS criminalId, b.id AS hitId,f.case_id AS caseId,b.name AS criminalName, b.matching_score AS MatchScore,d.name AS Country,e.name AS State,c.dob AS dob ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public LevelTwoData mapRow(ResultSet rs, int rowNum) throws SQLException{
        final Integer searchId = rs.getInt("searchId");
        final Integer criminalId = rs.getInt("criminalId");
        final Integer hitId = rs.getInt("hitId");
        final Integer caseId = rs.getInt("caseId");
        final String criminalName = rs.getString("criminalName");
        final Integer matchScore = rs.getInt("matchScore");
        final String country = rs.getString("country");
        final String state = rs.getString("state");
        final String dob = rs.getString("dob");
        return LevelTwoData.newInstance(searchId,criminalId,hitId,caseId,criminalName,matchScore,country,state,dob);
    }
}
