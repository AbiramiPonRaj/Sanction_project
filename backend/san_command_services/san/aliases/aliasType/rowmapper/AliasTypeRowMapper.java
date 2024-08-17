package com.ponsun.san.aliases.aliasType.rowmapper;




import com.ponsun.san.aliases.aliasType.data.AliasTypeData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@Service
@Slf4j
public class AliasTypeRowMapper implements RowMapper<AliasTypeData> {
    private final String schema;
    public AliasTypeRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM aliastype at ");
        this.schema = builder.toString();
    }
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("at.PrimaryKey as PrimaryKey, ");
        builder.append("at.ID as ID , ");
        builder.append("at.Text as Text, ");
        builder.append("at.FK_AliasTypeValues as FK_AliasTypeValues ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public AliasTypeData mapRow(ResultSet rs, int rowNum) throws SQLException{
        final String PrimaryKey = rs.getString("PrimaryKey");
        final String ID = rs.getString("ID");
        final String  Text = rs.getString("Text");
        final String FK_AliasTypeValues = rs.getString("FK_AliasTypeValues");
        return new AliasTypeData(PrimaryKey,ID,Text,FK_AliasTypeValues);
    }
}
