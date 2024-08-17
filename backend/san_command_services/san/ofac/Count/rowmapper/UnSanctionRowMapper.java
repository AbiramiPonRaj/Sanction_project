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
public class UnSanctionRowMapper implements RowMapper<SanctionDetailData> {
    private final String schema;

    public UnSanctionRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM `test_un_sanction` ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" dataid AS ids,UN_LIST_TYPE AS Group_Type,NATIONALITY_0 AS Nationality_country," +
                "INDIVIDUAL_ADDRESS_0_COUNTRY AS Citi_country," +
                "INDIVIDUAL_PLACE_OF_BIRTH_0_COUNTRY AS Birt_country," +
                "'' AS Iden_country," +
                "INDIVIDUAL_ADDRESS_0_COUNTRY AS Addr_country ");
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
