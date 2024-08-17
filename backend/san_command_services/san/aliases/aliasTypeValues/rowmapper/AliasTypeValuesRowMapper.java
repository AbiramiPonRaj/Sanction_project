package com.ponsun.san.aliases.aliasTypeValues.rowmapper;




import com.ponsun.san.aliases.aliasTypeValues.data.AliasTypeValuesData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@Service
@Slf4j
public class AliasTypeValuesRowMapper implements RowMapper<AliasTypeValuesData> {
    private final String schema;
    public AliasTypeValuesRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM aliastypevalues atv ");
        this.schema = builder.toString();
    }
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("atv.PrimaryKey as PrimaryKey, ");
        builder.append("atv.FK_ReferenceValueSets as FK_ReferenceValueSets  ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public AliasTypeValuesData mapRow(ResultSet rs, int rowNum) throws SQLException{
        final String PrimaryKey = rs.getString("PrimaryKey");
        final String FK_ReferenceValueSets = rs.getString("FK_ReferenceValueSets");
        return new AliasTypeValuesData(PrimaryKey,FK_ReferenceValueSets);
    }
}
