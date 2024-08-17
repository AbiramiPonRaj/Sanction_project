package com.ponsun.san.ofac.Count.rowmapper;

import com.ponsun.san.ofac.Count.data.CountData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@Service
public class CountRowMapper implements RowMapper<CountData> {
    private final String schema;

    public CountRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM count pcal ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("pcal.id as id");
        builder.append("pcal.name as name");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public CountData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final String name = rs.getString("name");
        final String orgName= "";
        final Integer fileType=rs.getInt("fileType");
        final String fileList   = rs.getString("_list");
        return new CountData(id, name,orgName,fileType,fileList);
    }
}