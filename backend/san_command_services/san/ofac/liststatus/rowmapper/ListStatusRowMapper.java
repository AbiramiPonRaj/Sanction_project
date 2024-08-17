


package com.ponsun.san.ofac.liststatus.rowmapper;

import com.ponsun.san.ofac.LookUpResults.data.LookUpResultsData;
import com.ponsun.san.ofac.Program.data.ProgramData;
import com.ponsun.san.ofac.liststatus.data.ListStatusData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class ListStatusRowMapper implements RowMapper<ListStatusData> {
    private final String schema;
    public ListStatusRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" liststatus ");
        this.schema = builder.toString();
    }
    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.`FixedRef`");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public ListStatusData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String liststatus = rs.getString("liststatus");
        return new ListStatusData(liststatus);
    }
}