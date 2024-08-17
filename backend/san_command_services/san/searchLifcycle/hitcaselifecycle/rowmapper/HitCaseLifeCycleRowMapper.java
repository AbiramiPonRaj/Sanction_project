package com.ponsun.san.searchLifcycle.hitcaselifecycle.rowmapper;

import com.ponsun.san.searchLifcycle.hitcaselifecycle.data.HitCaseLifeCycleData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HitCaseLifeCycleRowMapper implements RowMapper<HitCaseLifeCycleData> {

    private final String schema;

    public HitCaseLifeCycleRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM hitcase_lifecycle chl ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("au.id as id");
        builder.append("au.caseId as caseId,");
        builder.append("au.levelId as levelId,");
        builder.append("au.remark as remark,");
        builder.append("au.statusId as statusId,");
        builder.append("au.dt as dt,");
        builder.append("au.valid as valid,");
        builder.append("au.uid as uid ");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public HitCaseLifeCycleData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final Integer caseId = rs.getInt("caseId");
        final Integer levelId = rs.getInt("levelId");
        final String remark = rs.getString("remark");
        final Integer statusId = rs.getInt("statusId");
        final String dt = rs.getString("dt");
        final Boolean valid = rs.getBoolean("valid");
        final Integer uid = rs.getInt("uid");
        return new HitCaseLifeCycleData(id,caseId, levelId, remark, statusId,uid,dt,valid);
    }
}
