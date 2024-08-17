package com.ponsun.san.sanction.nameDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.AddressFileData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class AddressFileRowMapper implements RowMapper<AddressFileData> {
    private final String schema;

    public AddressFileRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_eu_sanction a");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" a.Addr_number, a.Addr_street, a.Addr_zipcode, a.Addr_city, a.Addr_country, a.Addr_other , a.Entity_logical_id_Addr");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public AddressFileData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String Addr_number = rs.getString("Addr_number");
        final String Addr_street = rs.getString("Addr_street");
        final String Addr_zipcode = rs.getString("Addr_zipcode");
        final String Addr_city = rs.getString("Addr_city");
        final String Addr_country = rs.getString("Addr_country");
        final String Addr_other = rs.getString("Addr_other");
        final Integer Entity_logical_id_Addr = rs.getInt("Entity_logical_id_Addr");
        return new AddressFileData(Addr_number, Addr_street, Addr_zipcode, Addr_city, Addr_country ,Addr_other,Entity_logical_id_Addr);
    }
}
