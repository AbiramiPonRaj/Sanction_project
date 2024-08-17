package com.ponsun.san.adminconfiguration.resetpassword.rowmapper;

import com.ponsun.san.adminconfiguration.resetpassword.data.ResetPasswordData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetPasswordRowMapper implements RowMapper<ResetPasswordData> {

    private final String schema;

    public ResetPasswordRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM admin_users_reset_password au ");
        this.schema = builder.toString();
    }

    public String schema() {
        return this.schema;
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("au.id as id");
        builder.append("au.resetUid as resetUid");
        builder.append("au.password as password");
        builder.append("au.reason as reason");
        builder.append("au.changedUid as changedUid");
        builder.append("au.changedDate as changedDate");
        builder.append("au.changedTime as changedTime");
        builder.append("au.uid as uid");
        builder.append("au.euid as euid");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public ResetPasswordData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long resetUid = rs.getLong("resetUid");
        final String password = rs.getString("password");
        final String reason = rs.getString("reason");
        final Integer changedUid = rs.getInt("changedUid");
        final String changedDate = rs.getString("changedDate");
        final String changedTime = rs.getString("changedTime");
        final Integer uid = rs.getInt("uid");
        final Integer euid = rs.getInt("euid");
        return ResetPasswordData.newInstance(id, resetUid, password, reason, changedUid, changedDate, changedTime,uid,euid);
    }
}


