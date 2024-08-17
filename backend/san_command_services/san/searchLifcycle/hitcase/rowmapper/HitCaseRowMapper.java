package com.ponsun.san.searchLifcycle.hitcase.rowmapper;

import com.ponsun.san.searchLifcycle.hitcase.data.HitCaseData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HitCaseRowMapper  implements RowMapper <HitCaseData> {

    private final String schema;

    public HitCaseRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM hitcase ch ");
        this.schema = builder.toString();
    }

    public String schema() {
        return this.schema;
    }
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("ch.id as id, ");
        builder.append("ch.display as display, ");
        builder.append("ch.searchId as searchId, ");
        builder.append("ch.hitId as hitId, ");
        builder.append("ch.criminalId as criminalId, ");
        builder.append("ch.levelId as levelId, ");
        builder.append("ch.status_now_Id as statusNowId, ");
        builder.append("ch.cycle_id as cycleId, ");
        builder.append("ch.uid as uid, ");
//        builder.append("ch.dt as dt, ");
        builder.append("ch.valid as valid, ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
        public HitCaseData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final String display = rs.getString("display");
        final Integer searchId = rs.getInt("searchId");
        final Integer hitId = rs.getInt("hitId");
        final Integer criminalId = rs.getInt("criminalId");
        final Integer levelId = rs.getInt("levelId");
        final Integer statusNowId = rs.getInt("statusNowId");
        final Integer cycleId = rs.getInt("cycleId");
        final Integer uid = rs.getInt("uid");
//        final String dt = rs.getString("dt");
        final Boolean valid = rs.getBoolean("valid");
        return HitCaseData.newInstance(id,display,searchId,hitId,criminalId,levelId,statusNowId,cycleId,uid,valid);
    }
}
