package com.ponsun.san.ofac.lookup.rowmapper;

import com.ponsun.san.ofac.lookup.data.LookUpData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class LookUpRowMapper implements RowMapper<LookUpData> {
    private final String schema;

    public LookUpRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM lookUp pcal ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("pcal.id as id");
        builder.append("pcal.type as type");
        builder.append("pcal.name as name");
        builder.append("pcal.minimum_name_score as minimum_name_score");
        builder.append("pcal.address as address");
        builder.append("pcal.city as city");
        builder.append("pcal.state as state");
        builder.append("pcal.country as country");
        builder.append("pcal.list as list");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public LookUpData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final String type = rs.getString("type");
        final String name = rs.getString("name");
        final Integer minimum_name_score = rs.getInt("minimum_name_score");
        final String address = rs.getString("address");
        final String city = rs.getString("city");
        final String state = rs.getString("state");
        final String country = rs.getString("country");
        final String list = rs.getString("list");
        return new LookUpData(id, type, name, minimum_name_score, address, city, state, country, list);

    }
}