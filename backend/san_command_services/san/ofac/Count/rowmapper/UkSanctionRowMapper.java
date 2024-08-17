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
public class UkSanctionRowMapper implements RowMapper<SanctionDetailData> {
    private final String schema;

    public UkSanctionRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM `test_uk_sanction` ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" Group_id AS ids," +
                "    Group_Type AS Group_Type," +
                "    COALESCE(GROUP_CONCAT(DISTINCT Nationality), '') AS Nationality_country," +
                "    COALESCE(GROUP_CONCAT(DISTINCT National_Identification_Details), '') AS Citi_country," +
                "    COALESCE(GROUP_CONCAT(DISTINCT Country_of_Birth), '') AS Birt_country," +
                "    COALESCE(GROUP_CONCAT(DISTINCT Passport_Details), '') AS Iden_country," +
                "    COALESCE(GROUP_CONCAT(DISTINCT Country), '') AS Addr_country");
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
