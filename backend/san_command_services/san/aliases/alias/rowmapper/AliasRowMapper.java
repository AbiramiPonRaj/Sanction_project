package com.ponsun.san.aliases.alias.rowmapper;



import com.ponsun.san.aliases.alias.data.AliasData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@Service
@Slf4j
public class AliasRowMapper implements RowMapper<AliasData> {
    private final String schema;
    public AliasRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM alias a ");
        this.schema = builder.toString();
    }
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.PrimaryKey as PrimaryKey, ");
        builder.append("a.FixedRef as FixedRef , ");
        builder.append("a.AliasTypeID as AliasTypeID, ");
        builder.append("a.Primary as Primary, ");
        builder.append("a.LowQuality as LowQuality, ");
        builder.append("a.FK_Identity as FK_Identity ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public AliasData mapRow(ResultSet rs, int rowNum) throws SQLException{
        final String PrimaryKey = rs.getString("PrimaryKey");
        final String FixedRef = rs.getString("FixedRef");
        final String AliasTypeID = rs.getString("AliasTypeID");
        final String  Primary = rs.getString("Primary");
        final String LowQuality = rs.getString("LowQuality");
        final String FK_Identity = rs.getString("FK_Identity");
        return new AliasData(PrimaryKey,FixedRef,AliasTypeID,Primary,LowQuality,FK_Identity);
    }
}
