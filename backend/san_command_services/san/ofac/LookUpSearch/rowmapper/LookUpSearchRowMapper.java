package com.ponsun.san.ofac.LookUpSearch.rowmapper;

import com.ponsun.san.ofac.LookUpSearch.data.LookUpSearchData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class LookUpSearchRowMapper  implements RowMapper<LookUpSearchData> {
    private final String schema;

    public LookUpSearchRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM identity a, alias b, documentedname c, documentednamepart d, namepartvalue e ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);

        builder.append(" a.`FixedRef` AS ids, e.`Text` AS NAME");

        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public LookUpSearchData mapRow(ResultSet rs, int rowNum) throws SQLException {

        final Integer ids = rs.getInt("ids");
        final String NAME = rs.getString("NAME");
        return new LookUpSearchData(ids,NAME);

    }
}


