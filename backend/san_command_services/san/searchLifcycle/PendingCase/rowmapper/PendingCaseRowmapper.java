package com.ponsun.san.searchLifcycle.PendingCase.rowmapper;

import com.ponsun.san.searchLifcycle.PendingCase.data.PendingCaseData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class PendingCaseRowmapper implements RowMapper<PendingCaseData> {
    public final String schema;
//    public PendingCaseRowmapper(){
//
//        final StringBuilder builder = new StringBuilder(200);
//        builder.append(" FROM crm_hitcase  a,crm_hitdata b,crm_hitdata_lifecycle c");
//        this.schema = builder.toString();
//    }
//final StringBuilder builder = new StringBuilder(200);
//    builder.append(" FROM crm_search a,crm_hitdata b, crm_hitdata_lifecycle c");
//    this.schema = builder.toString();
//}
public PendingCaseRowmapper(){

    final StringBuilder builder = new StringBuilder(200);
    builder.append(" FROM search a,hitrecord b, hitrecord_lifecycle c,hitcase d");
    this.schema = builder.toString();
}
    public String schema()
    {return this.schema;
    }

    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("d.id AS caseId,a.id AS searchId, b.id AS hitId, b.criminalid AS criminalId, b.name AS criminalName, b.matchingscore AS matchingScore,c.remark AS remark,c.created_At AS created_at ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public PendingCaseData mapRow(ResultSet rs, int rowNum) throws SQLException {

        final Integer caseId = rs.getInt("caseId");
        final Integer searchId = rs.getInt("searchId");
        final Integer hitId = rs.getInt("hitId");
        final Integer criminalId = rs.getInt("criminalId");
        final String criminalName = rs.getString("criminalName");
        final Integer matchingScore = rs.getInt("matchingScore");
        final String remark = rs.getString("remark");
        final String createdAt = rs.getString("created_at");

        //caseId, criminalId, hitId, levelId,searchId,statusId,uid,criminalName,matchingScore, remark
        return PendingCaseData.newInstance(caseId, criminalId, hitId, 0,searchId,0,0,criminalName,matchingScore, remark,createdAt);
    }

}
//