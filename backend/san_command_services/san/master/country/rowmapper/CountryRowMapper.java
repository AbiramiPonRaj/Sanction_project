package com.ponsun.san.master.country.rowmapper;



import com.ponsun.san.master.country.data.CountryData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@Service
@Slf4j
public class CountryRowMapper implements RowMapper<CountryData> {
    private final String schema;
    public CountryRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM country c ");
        this.schema = builder.toString();
    }
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("c.PrimaryKey as PrimaryKey, ");
        builder.append("c.ID as ID , ");
        builder.append("c.ISO2 as ISO2, ");
        builder.append("c.Text as Text, ");
        builder.append("c.FK_CountryValues as FK_CountryValues ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public CountryData mapRow(ResultSet rs, int rowNum) throws SQLException{
        final String PrimaryKey = rs.getString("PrimaryKey");
        final String ID = rs.getString("ID");
        final String ISO2 = rs.getString("ISO2");
        final String  Text = rs.getString("Text");
        final String FK_CountryValues = rs.getString("FK_CountryValues");
        return new CountryData(PrimaryKey,ID,ISO2,Text,FK_CountryValues);
    }
}
