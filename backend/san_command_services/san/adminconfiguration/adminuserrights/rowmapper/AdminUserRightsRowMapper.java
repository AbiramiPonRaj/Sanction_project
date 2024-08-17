package com.ponsun.san.adminconfiguration.adminuserrights.rowmapper;


import com.ponsun.san.adminconfiguration.adminuserrights.data.AdminUserRightsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUserRightsRowMapper implements RowMapper<AdminUserRightsDTO> {
    private final String schema;

    public AdminUserRightsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM admin_user_rights au");
        this.schema = builder.toString();
    }

    public String schema() {return this.schema;}

    public String  tableSchema() {
        final StringBuilder builder = new StringBuilder(200);

        builder.append("au.id as id");
        builder.append("au.uid as uid, ");
        builder.append("au.modId as modId, ");
        builder.append("au.modDetId as modDetId, ");
        builder.append("au.entUid as entUid,");
        builder.append("au.dt as dt, ");
        builder.append("au.valid as valid, ");
        builder.append("au.euid as euid");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public AdminUserRightsDTO mapRow (ResultSet rs, int rowNum) throws SQLException {
        final String id = rs.getNString("id");
        final String uid = rs.getString("uid");
        final Integer modId = rs.getInt("modId");
        final Integer modDetId = rs.getInt("modDetId");
        final Integer entUid = rs.getInt("entUid");
        final String dt = rs.getString("dt");
        final Boolean valid =rs.getBoolean("valid");
        return AdminUserRightsDTO.newInstance(id,uid,modId,modDetId,entUid,dt,valid);
    }


}
