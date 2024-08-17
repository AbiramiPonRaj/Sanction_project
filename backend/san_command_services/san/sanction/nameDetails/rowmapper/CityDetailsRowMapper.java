package com.ponsun.san.sanction.nameDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.CityDetailsData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class CityDetailsRowMapper implements RowMapper<CityDetailsData> {
    private final String schema;

    public CityDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_eu_sanction a");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" GROUP_CONCAT(a.Citi_country) as Citi_country, a.Entity_logical_id_citi");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public CityDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String Citi_country = rs.getString("Citi_country");
        final Integer Entity_logical_id_citi = rs.getInt("Entity_logical_id_citi");
        return new CityDetailsData(Citi_country , Entity_logical_id_citi );

    }
}
