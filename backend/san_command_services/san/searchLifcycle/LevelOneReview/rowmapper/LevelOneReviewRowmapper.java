package com.ponsun.san.searchLifcycle.LevelOneReview.rowmapper;


import com.ponsun.san.searchLifcycle.LevelOneReview.data.LevelOneReviewData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class LevelOneReviewRowmapper implements RowMapper<LevelOneReviewData> {
    public final String schema;

    public LevelOneReviewRowmapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM  hitrecord b, hitrecord_lifecycle c, hitcase d ");
        this.schema = builder.toString();
    }
    public String schema(){return this.schema;}

    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("d.id AS caseId, b.id AS hitId,b.criminalid AS criminalId,b.name AS criminalName, b.matchingscore AS matchingScore,c.remark AS remark,c.created_at AS createdAt,\n" +
                "    (SELECT a.id FROM search a \n" +
                "     WHERE a.id = b.searchid AND a.id = c.search_id LIMIT 1) AS searchId ");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public LevelOneReviewData mapRow(ResultSet rs, int rowNum) throws SQLException{
        final Integer caseId = rs.getInt("caseId");
        final Integer hitId = rs.getInt("hitId");
        final Integer criminalId = rs.getInt("criminalId");
        final String criminalName = rs.getString("criminalName");
        final Integer matchingScore = rs.getInt("matchingScore");
        final String remark = rs.getString("remark");
        final String createdAt = rs.getString("createdAt");
        final Integer searchId = rs.getInt("searchId");
        return LevelOneReviewData.newInstance(caseId,hitId,criminalId,criminalName,matchingScore,remark,createdAt,searchId);
    }
}
