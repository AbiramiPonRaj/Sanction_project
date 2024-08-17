package com.ponsun.san.sanction.unDetails.rowmapper;


import com.ponsun.san.sanction.nameDetails.data.WholeNameData;
import com.ponsun.san.sanction.unDetails.data.UnAliasDetailsData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class UnAliasDetailsRowMapper implements RowMapper<UnAliasDetailsData> {
    private final String schema;

    public UnAliasDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_un_sanction  a");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a._Type AS type,a.name  AS name,quality AS quality");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public UnAliasDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String _Type = rs.getString("_Type");
        final String name = rs.getString("name");
        final String quality = rs.getString("quality");
        return new UnAliasDetailsData (_Type, name ,quality);
    }
}
