package com.ponsun.san.sanction.nameDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.PersonalDetailsData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class PersonalDetailsRowMapper implements RowMapper<PersonalDetailsData> {
    private final String schema;

    public PersonalDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_eu_sanction a");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" a.Naal_firstname,a.Naal_middlename,a.Naal_lastname,a.Entity_logical_id,a.Naal_wholename ");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public PersonalDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String Naal_firstname = rs.getString("Naal_firstname");
        final String Naal_middlename = rs.getString("Naal_middlename");
        final String Naal_lastname = rs.getString("Naal_lastname");
        final String Naal_wholename = rs.getString("Naal_wholename");
        final Integer Entity_logical_id = rs.getInt("Entity_logical_id");
        return new PersonalDetailsData(Naal_firstname, Naal_middlename, Naal_lastname, Naal_wholename, Entity_logical_id);
    }
}
