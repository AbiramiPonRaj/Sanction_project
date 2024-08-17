package com.ponsun.san.adminconfiguration.admingroup.rowmapper;

import com.ponsun.san.adminconfiguration.admingroup.data.AdmingroupData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdmingoupRowMapper implements RowMapper<AdmingroupData> {

    private final String schema;

    public AdmingoupRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM admin_config_admingroup ac ");
        this.schema = builder.toString();
    }

    public String schema() {
        return this.schema;
    }
    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("ac.id as id, ");
        builder.append("ac.module_url as moduleUrl, ");
        builder.append("ac.name as name, ");
        builder.append("ac.valid as valid, ");
        builder.append("ac.uid as uid");
        builder.append("ac.euid as euid");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public AdmingroupData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final String moduleUrl = rs.getString("moduleUrl");
        final String name = rs.getString("name");
        final Boolean valid = rs.getBoolean("valid");
        final Integer uid = rs.getInt("uid");
        final Integer euid = rs.getInt("euid");
        return AdmingroupData.newInstance(id,moduleUrl,name,valid,uid,euid);
    }
}
