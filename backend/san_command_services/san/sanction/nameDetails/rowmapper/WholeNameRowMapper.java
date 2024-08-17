package com.ponsun.san.sanction.nameDetails.rowmapper;


import com.ponsun.san.sanction.nameDetails.data.WholeNameData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class WholeNameRowMapper implements RowMapper<WholeNameData> {
    private final String schema;

    public WholeNameRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_eu_sanction a");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.Entity_logical_id AS id,a.Naal_wholename  AS NAME,2 AS fileType");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public WholeNameData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final String NAME = rs.getString("NAME");
        final String fileType = rs.getString("fileType");
        return new WholeNameData(id, NAME,fileType);

    }
}
