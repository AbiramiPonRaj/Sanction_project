package com.ponsun.san.master.list.rowmapper;




import com.ponsun.san.master.list.data.ListData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@Service
@Slf4j
public class ListRowMapper implements RowMapper<ListData> {
    private final String schema;
    public ListRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM list c ");
        this.schema = builder.toString();
    }
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("c.PrimaryKey as PrimaryKey, ");
        builder.append("c.ID as ID , ");
        builder.append("c.Text as Text, ");
        builder.append("c.FK_ListValues as FK_ListValues ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public ListData mapRow(ResultSet rs, int rowNum) throws SQLException{
        final String PrimaryKey = rs.getString("PrimaryKey");
        final String ID = rs.getString("ID");
        final String  Text = rs.getString("Text");
        final String FK_ListValues = rs.getString("FK_ListValues");
        return new ListData(PrimaryKey,ID,Text,FK_ListValues);
    }
}
