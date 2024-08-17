package com.ponsun.san.sanction.nameDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.NationalIdentificationData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class NationalIdentificationNumberRowMapper implements RowMapper<NationalIdentificationData> {
    private final String schema;

    public NationalIdentificationNumberRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM (" +
                " SELECT 'Passport ' as identity,Passport_Number as number,Passport_Details as det FROM test_uk_sanction WHERE Group_ID=? AND Passport_Number!=''" +
                " UNION " +
                " SELECT 'National_Identification_Number ' as identity,National_Identification_Number as number,National_Identification_Details as det FROM test_uk_sanction WHERE Group_ID=? AND National_Identification_Number!='') a");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append ( "");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public NationalIdentificationData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String Passport = rs.getString("identity");
        final String Passport_Number = rs.getString("number");
        final String Passport_Details = rs.getString("det");

        final Integer Group_ID = 0;
        return new NationalIdentificationData(Passport , Passport_Number,Passport_Details,Group_ID);

    }
}
