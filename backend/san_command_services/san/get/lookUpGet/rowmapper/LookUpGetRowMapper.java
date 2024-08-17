package com.ponsun.san.get.lookUpGet.rowmapper;



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
public class LookUpGetRowMapper implements RowMapper<CountryData> {
    private final String schema;
    public LookUpGetRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM  ");
        this.schema = builder.toString();
    }
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("c.Name as Name, ");
        builder.append("c.Address as Address , ");
        builder.append("c.Type as Type, ");
        builder.append("c.Program as Program, ");
        builder.append("c.List as List ");
        builder.append("c.Score as Score ");
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
