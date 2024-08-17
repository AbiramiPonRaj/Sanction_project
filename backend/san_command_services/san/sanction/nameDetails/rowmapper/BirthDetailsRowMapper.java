package com.ponsun.san.sanction.nameDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.BirthDetailsData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class BirthDetailsRowMapper implements RowMapper<BirthDetailsData> {
    private final String schema;

    public BirthDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_eu_sanction");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" Entity_logical_id_birth,GROUP_CONCAT(Birt_date) AS Birt_date,GROUP_CONCAT(Birt_plcae) AS Birt_plcae ,GROUP_CONCAT(Birt_country) AS Birt_country");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public BirthDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer Entity_logical_id_birth = rs.getInt("Entity_logical_id_birth");
        final String Birt_date = rs.getString("Birt_date");
        final String Birt_plcae = rs.getString("Birt_plcae");
        final String Birt_country = rs.getString("Birt_country");
        return new BirthDetailsData(Entity_logical_id_birth, Birt_date, Birt_plcae, Birt_country);
    }
}
