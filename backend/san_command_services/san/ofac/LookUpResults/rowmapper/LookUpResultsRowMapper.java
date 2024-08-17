package com.ponsun.san.ofac.LookUpResults.rowmapper;

import com.ponsun.san.ofac.LookUpResults.data.LookUpResultsData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class LookUpResultsRowMapper implements RowMapper<LookUpResultsData> {
    private final String schema;
    public LookUpResultsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM distinctparty a,PROFILE b,identity c,alias d,documentedname e,documentednamepart f,namepartvalue g ");
        this.schema = builder.toString();
    }
    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.`FixedRef` AS Ids,g.`Text` AS NAME,IFNULL(Address(a.FixedRef),'') AS address,IFNULL(`Type`(b.`PartySubTypeID`),'') AS EntityType,Program(a.FixedRef) AS Program,liststatus(a.FixedRef) AS LIST,100 AS Score");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public LookUpResultsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer Ids = rs.getInt("Ids");
        final String NAME = rs.getString("NAME");
        final String address = rs.getString("address");
        final String EntityType = rs.getString("EntityType");
        final String program = rs.getString("Program");
        final String LIST = rs.getString("LIST");
        final Integer Score = rs.getInt("Score");
        return new LookUpResultsData(Ids, NAME, address, EntityType, program, LIST, Score);
    }
}