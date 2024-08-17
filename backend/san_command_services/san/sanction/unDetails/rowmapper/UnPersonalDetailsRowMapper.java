package com.ponsun.san.sanction.unDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.PersonalDetailsData;
import com.ponsun.san.sanction.unDetails.data.UnPersonalDetailsData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class UnPersonalDetailsRowMapper implements RowMapper<UnPersonalDetailsData> {
    private final String schema;

    public UnPersonalDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_un_sanction ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" UN_LIST_TYPE AS _list, TITLE_0 AS title, FIRST_NAME AS firstName, SECOND_NAME AS secName, " +
                "THIRD_NAME AS ThirdName, GENDER, INDIVIDUAL_DATE_OF_BIRTH_0_TYPE_OF_DATE AS BirthType, " +
                "CONCAT(INDIVIDUAL_DATE_OF_BIRTH_0_YEAR, ' ', INDIVIDUAL_DATE_OF_BIRTH_0_YEAR, ' ', " +
                "INDIVIDUAL_DATE_OF_BIRTH_0_FROM_YEAR, '-', INDIVIDUAL_DATE_OF_BIRTH_0_TO_YEAR, ' ', " +
                "INDIVIDUAL_ALIAS_1_DATE_OF_BIRTH, ' ', INDIVIDUAL_ALIAS_2_DATE_OF_BIRTH) AS dob, " +
                "CONCAT(INDIVIDUAL_PLACE_OF_BIRTH_0_CITY, ',', INDIVIDUAL_PLACE_OF_BIRTH_0_STATE_PROVINCE) AS BirthPlace, " +
                "NATIONALITY_0 AS Nationality, CONCAT(NATIONALITY_0, ',', INDIVIDUAL_PLACE_OF_BIRTH_0_COUNTRY) AS Citizenship, " +
                "COMMENTS1 AS Remarks , DATAID ");
        builder.append(" FROM test_un_sanction ");
        return builder.toString();
    }


    @Override
    public UnPersonalDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String _list = rs.getString("_list");
        final String title = rs.getString("title");
        final String firstName = rs.getString("firstName");
        final String secName = rs.getString("secName");
        final String ThirdName = rs.getString("ThirdName");
        final String GENDER = rs.getString("GENDER");
        final String BirthType = rs.getString("BirthType");
        final String dob = rs.getString("dob");
        final String BirthPlace = rs.getString("BirthPlace");
        final String Nationality = rs.getString("Nationality");
        final String Citizenship = rs.getString("Citizenship");
        final String Remarks = rs.getString("Remarks");
        final Integer DATAID = rs.getInt("DATAID");
        return new UnPersonalDetailsData(_list, title, firstName,secName, ThirdName,GENDER,BirthType,dob,BirthPlace,Nationality,Citizenship,Remarks,DATAID);
    }
}
