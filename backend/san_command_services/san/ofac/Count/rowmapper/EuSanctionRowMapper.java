package com.ponsun.san.ofac.Count.rowmapper;

import com.ponsun.san.ofac.Count.data.SanctionDetailData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class EuSanctionRowMapper implements RowMapper<SanctionDetailData> {
    private final String schema;

    public EuSanctionRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_eu_sanction ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" Entity_logical_id as ids,Subject_type AS Group_Type," +
                "    '' AS Nationality_country," +
                "    TRIM(BOTH ',' FROM GROUP_CONCAT(DISTINCT Citi_country SEPARATOR ',')) AS Citi_country," +
                "    TRIM(BOTH ',' FROM GROUP_CONCAT(DISTINCT Birt_country SEPARATOR ',')) AS Birt_country," +
                "    TRIM(BOTH ',' FROM GROUP_CONCAT(DISTINCT Iden_country SEPARATOR ',')) AS Iden_country," +
                "    TRIM(BOTH ',' FROM GROUP_CONCAT(DISTINCT Addr_country SEPARATOR ',')) AS Addr_country ");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public SanctionDetailData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer ids=rs.getInt("ids");
        final String Group_Type = rs.getString("Group_Type");
        final String Nationality_country= rs.getString("Nationality_country");
        final String Citi_country= rs.getString("Citi_country");
        final String Birt_country= rs.getString("Birt_country");
        final String Iden_country= rs.getString("Iden_country");
        final String Addr_country= rs.getString("Addr_country");
        return new SanctionDetailData(ids,Group_Type,Nationality_country,Citi_country,Birt_country,Iden_country,Addr_country);
    }
}

