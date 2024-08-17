//package com.ponsun.san.ofac.Program.rowmapper;
//
//public class ProgramRowMapper {
//}


package com.ponsun.san.ofac.Program.rowmapper;

import com.ponsun.san.ofac.LookUpResults.data.LookUpResultsData;
import com.ponsun.san.ofac.Program.data.ProgramData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class ProgramRowMapper implements RowMapper<ProgramData> {
    private final String schema;
    public ProgramRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" Program ");
        this.schema = builder.toString();
    }
    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.`FixedRef`");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public ProgramData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String Program = rs.getString("Program");
        return new ProgramData(Program);
    }
}