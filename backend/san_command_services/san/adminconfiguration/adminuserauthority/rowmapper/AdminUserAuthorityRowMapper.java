package com.ponsun.san.adminconfiguration.adminuserauthority.rowmapper;

import com.ponsun.san.adminconfiguration.adminuserauthority.data.AdminUserAuthorityData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUserAuthorityRowMapper implements RowMapper<AdminUserAuthorityData> {
    private final String schema;

    public AdminUserAuthorityRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM admin_user_authority au");
        this.schema = builder.toString();
    }
    public String schema() {return this.schema;}

    public String  tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("au.id as id");
        builder.append("au.roleCode as roleCode");
        builder.append("au.roleDescription as roleDescription");
        builder.append("au.valid as valid");
        builder.append("au.uid as uid");
        builder.append("au.euid as euid");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
public AdminUserAuthorityData mapRow (ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final String roleCode = rs.getString("roleCode");
        final String roleDescription = rs.getString("roleDescription");
        final Boolean valid = rs.getBoolean("valid");
        final Integer uid = rs.getInt("uid");
        final Integer euid = rs.getInt("euid");
        return  AdminUserAuthorityData.newInstance(id,roleCode,roleDescription,valid,uid,euid);
    }
}