package com.ponsun.san.searchLifcycle.LevelTwoReview.rowmapper;


import com.ponsun.san.searchLifcycle.LevelTwoReview.data.LevelTwoReviewData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class LevelTwoReviewRowmapper implements RowMapper<LevelTwoReviewData> {
    public final String schema;

    public LevelTwoReviewRowmapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM hitrecord b,hitrecord_lifecycle c,hitcase d ");
        this.schema = builder.toString();
    }
    public String schema(){return this.schema;}

    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("d.id AS caseId,b.id AS hitId,b.criminalid AS criminalId,b.name AS criminalName,b.matchingscore AS matchingScore,c.remark AS remark,c.created_At AS createdAt,\n" +
                "(SELECT a.id AS searchId FROM search a\n" +
                " WHERE a.id= b.searchid AND a.id=c.search_id LIMIT 1) AS searchId");
        builder.append(this.schema);
        return builder.toString();
    }
@Override
    public LevelTwoReviewData mapRow(ResultSet rs,int rowNum) throws SQLException{
        final Integer caseId = rs.getInt("caseId");
        final Integer hitId = rs.getInt("hitId");
        final Integer criminalId = rs.getInt("criminalId");
        final String criminalName = rs.getString("criminalName");
        final Integer matchingScore = rs.getInt("matchingScore");
        final String remark= rs.getString("remark");
        final String createdAt= rs.getString("createdAt");
        final Integer searchId= rs.getInt("searchId");
        return LevelTwoReviewData.newInstance(caseId,hitId,criminalId,criminalName,matchingScore,remark,createdAt,searchId);
}
}
