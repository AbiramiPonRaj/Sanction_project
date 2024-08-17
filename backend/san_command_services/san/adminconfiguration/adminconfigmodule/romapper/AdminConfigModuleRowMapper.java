package com.ponsun.san.adminconfiguration.adminconfigmodule.romapper;

import com.ponsun.san.adminconfiguration.adminconfigmodule.data.AdminConfigModuleData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j

public class AdminConfigModuleRowMapper implements RowMapper<AdminConfigModuleData> {
    private final String schema;

    public AdminConfigModuleRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM admin_config_module ad ");
        this.schema = builder.toString();
    }
    public String schema() { return this.schema; }
    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("ad.id as id");
        builder.append("ad.name as name");
        builder.append("ad.valid as valid");
        builder.append("ad.orderno as orderno");
        builder.append("ad.uid as uid");
        builder.append("ad.euid as euid");
        builder.append(this.schema);
        return builder.toString();
    }
        @Override

        public AdminConfigModuleData mapRow(ResultSet rs , int rowNum) throws SQLException {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("name");
            final Boolean valid = rs.getBoolean("valid");
            final Integer orderno = rs.getInt("orderno");
            final Integer uid = rs.getInt("uid");
            final Integer euid = rs.getInt("euid");
            return AdminConfigModuleData.newInstance(id,name,valid,orderno,uid,euid);
        }
    }
