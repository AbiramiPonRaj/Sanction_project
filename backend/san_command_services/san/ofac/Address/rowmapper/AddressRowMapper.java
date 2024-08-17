

package com.ponsun.san.ofac.Address.rowmapper;

import com.ponsun.san.ofac.Address.data.AddressData;
import com.ponsun.san.ofac.Program.data.ProgramData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class AddressRowMapper implements RowMapper<AddressData> {
    private final String schema;
    public AddressRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" Address ");
        this.schema = builder.toString();
    }
    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.`FixedRef`");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public AddressData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String Address = rs.getString("Address");
        return new AddressData(Address);
    }
}