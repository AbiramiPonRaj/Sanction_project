package com.ponsun.san.master.all.rowmapper;




import com.ponsun.san.master.all.data.AllData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@Service
@Slf4j
public class AllRowMapper implements RowMapper<AllData> {
    private final String schema;
    public AllRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM partytype a , partysubtype b  ");
        this.schema = builder.toString();
    }
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" a.ID AS partyTypeID , b.ID AS partySubTypeID, a.Text AS partyText , b.Text AS partySubText , IF(b.Text = 'Unknown' , a.Text , b.Text) AS type_text ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public AllData mapRow(ResultSet rs, int rowNum) throws SQLException{
        final String partyTypeID = rs.getString("partyTypeID");
        final String partySubTypeID = rs.getString("partySubTypeID");
        final String partyText = rs.getString("partyText");
        final String partySubText = rs.getString("partySubText");
        final String type_text = rs.getString("type_text");
        return new AllData(partyTypeID,partySubTypeID,partyText,partySubText,type_text);
    }
}
