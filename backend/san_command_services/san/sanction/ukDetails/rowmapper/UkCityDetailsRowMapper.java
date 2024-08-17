package com.ponsun.san.sanction.ukDetails.rowmapper;


import com.ponsun.san.sanction.ukDetails.data.UkAliasDetailsData;
import com.ponsun.san.sanction.ukDetails.data.UkCityDetailsData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class UkCityDetailsRowMapper implements RowMapper<UkCityDetailsData> {
    private final String schema;

    public UkCityDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_uk_sanction ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" CONCAT(name_1,' ',name_2,' ',name_3,' ',name_4,' ',name_5,'',name_6) AS NAME," +
                " (IF(DOB,'','')) AS dob,Town_of_Birth AS Place_of_Birth,(Nationality) AS Citizenship,Group_Type , Group_ID ");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public UkCityDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String NAME = rs.getString("NAME");
        final String dob = rs.getString("dob");
        final String Place_of_Birth = rs.getString("Place_of_Birth");
        final String Citizenship = rs.getString("Citizenship");
        final String Group_Type = rs.getString("Group_Type");
        final Integer Group_ID = rs.getInt("Group_ID");

        return new UkCityDetailsData(NAME,dob , Place_of_Birth , Citizenship ,  Group_Type , Group_ID);
    }
}
