package com.ponsun.san.adminconfiguration.adminconfigmoduledet.rowmapper;

import com.ponsun.san.adminconfiguration.adminconfigmoduledet.data.AdminConfigModuleDetData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminConfigModuleDetRowMapper implements RowMapper<AdminConfigModuleDetData> {
    private final String schema;

    public AdminConfigModuleDetRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM admin_config_module_det cm ");
        this.schema = builder.toString();
    }

    public String schema() {
        return this.schema;
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("cm.id as id, ");
        builder.append("cm.modId as modId,");//
        builder.append("cm.name as name,");
        builder.append("cm.valid as valid,");
        builder.append("cm.url as url,");
        builder.append("cm.uid as uid");
        builder.append("cm.euid as euid");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
public AdminConfigModuleDetData mapRow(ResultSet rs, int rowNum) throws SQLException {
    final Integer id = rs.getInt("id");
    final Integer modId = rs.getInt("modId");
    final String name = rs.getString("name");
    final Boolean valid = rs.getBoolean("valid");
    final String url = rs.getString("url");
    final Integer uid = rs.getInt("uid");
    final Integer euid = rs.getInt("euid");
    return AdminConfigModuleDetData.newInstance(id,modId,name,valid,url,uid,euid);
    }
}
