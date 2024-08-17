package com.ponsun.san.sanction.nameDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.NameDetailsData;
import com.ponsun.san.sanction.nameDetails.domain.NameDetails;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class NameDetailsRowMapper implements RowMapper<NameDetailsData> {
    private final String schema;

    public NameDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM uk_san a");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.Title as Title, ");
        builder.append("a.Name_1 as Name_1, ");
        builder.append("a.Gender as Gender, ");
        builder.append("a.DOB as DOB, ");
        builder.append("a.Town_of_Birth as Town_of_Birth ");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public NameDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String Title = rs.getString("Title");
        final String Name_1 = rs.getString("Name_1");
        final String Gender = rs.getString("Gender");
        final String DOB = rs.getString("DOB");
        final String Town_of_Birth = rs.getString("Town_of_Birth");
        return new NameDetailsData(Title, Name_1, Gender, DOB, Town_of_Birth);

    }
}
