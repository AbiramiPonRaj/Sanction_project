package com.ponsun.san.searchLifcycle.rif.rowmapper;

import com.ponsun.san.searchLifcycle.rif.data.LevelFourData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class LevelFourRowmapper implements RowMapper<LevelFourData> {
    public final String schema;
    public LevelFourRowmapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM search a,hitrecord b,hitrecord_lifecycle f");
        this.schema = builder.toString();
    }
    public String schema(){ return this.schema;}
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.id AS searchId,b.criminalId AS criminalId, b.id AS hitId,f.case_id AS caseId,b.name AS criminalName,f.remark AS remark,f.level_id AS levelId, b.matchingScore AS MatchScore ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public LevelFourData mapRow(ResultSet rs,int rowNum) throws SQLException{
        final Integer searchId = rs.getInt("searchId");
        final Integer criminalId = rs.getInt("criminalId");
        final Integer hitId = rs.getInt("hitId");
        final Integer caseId = rs.getInt("caseId");
        final String criminalName = rs.getString("criminalName");
        final String remark = rs.getString("remark");
        final String levelId = rs.getString("levelId");

        final Integer matchScore = rs.getInt("MatchScore");

        final String country = "";//rs.getString("Country");
        final String state = "";//rs.getString("state");
        final String dob = "";//rs.getString("dob");
        return LevelFourData.newInstance(searchId,criminalId,hitId,caseId,criminalName,remark,levelId,matchScore,country,state,dob);
    }
}
