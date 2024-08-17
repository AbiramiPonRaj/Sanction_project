package com.ponsun.san.searchLifcycle.hitrecordlifecycle.rowmapper;


import com.ponsun.san.searchLifcycle.hitrecordlifecycle.data.HitRecordLifeCycleData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class HitRecordLifecycleRowMapper implements RowMapper<HitRecordLifeCycleData> {
    private  final String schema;

    public HitRecordLifecycleRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM hitcase_lifecycle au");
        this.schema = builder.toString();
    }
    public String schema() {
        return this.schema;
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("au.id as id, ");
        builder.append("au.searchId as searchId, ");
        builder.append("au.hitdataId as hitdataId, ");
        builder.append("au.criminalId as criminalId, ");
        builder.append("au.caseId as caseId, ");
        builder.append("au.levelId as levelId, ");
        builder.append("au.statusId as statusId, ");
        builder.append("au.remark as remark, ");
        builder.append("au.uid as uid, ");
        builder.append("au.dt as dt, ");
        builder.append("au.valid as valid, ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public HitRecordLifeCycleData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final Integer searchId = rs.getInt("searchId");
        final Integer hitdataId = rs.getInt("hitdataId");
        final Integer criminalId = rs.getInt("criminalId");
        final Integer caseId  = rs.getInt("caseId");
        final Integer levelId = rs.getInt("levelId");
        final Integer statusId = rs.getInt("statusId");
        final Integer uid = rs.getInt("uid");
        LocalDateTime dt = LocalDateTime.now(); // Replace with your LocalDateTime instance
        String formattedDateTime = dt.toString();
        final Boolean valid = rs.getBoolean("valid");
        final String remark = rs.getString("remark");

        return HitRecordLifeCycleData.newInstance(id, searchId, hitdataId, criminalId, caseId, levelId, statusId, uid, dt,valid,remark);
    }
}
