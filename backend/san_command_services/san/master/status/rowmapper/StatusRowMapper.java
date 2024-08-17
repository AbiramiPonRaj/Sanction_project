package com.ponsun.san.master.status.rowmapper;

import com.ponsun.san.master.status.data.StatusData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class StatusRowMapper implements RowMapper<StatusData> {

    private final String schema;

    public StatusRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM crm_config_status status ");
        this.schema = builder.toString();
    }

 

    public String schema() {
        return this.schema;
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("status.id as id");
        builder.append("status.code as code");
        builder.append("status.name as name");
        builder.append("status.valid as valid");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override

    public StatusData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final String code = rs.getString("code");
        final String name = rs.getString("name");
        final Boolean valid = rs.getBoolean("valid");
        return StatusData.newInstance(id, code, name, valid);
    }
}