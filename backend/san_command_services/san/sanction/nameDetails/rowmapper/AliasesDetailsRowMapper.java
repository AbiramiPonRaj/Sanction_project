package com.ponsun.san.sanction.nameDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.AliasesDetailsData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class AliasesDetailsRowMapper implements RowMapper<AliasesDetailsData> {
    private final String schema;
    public AliasesDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_uk_sanction ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" Alias_Type AS Alias_Type , Alias_Quality AS Alias_Quality ,CONCAT(name_1,' ',name_2,' ',name_3,' ',name_4,' ',name_5,'',name_6) AS NAME , Group_ID");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public AliasesDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String Alias_Type = rs.getString("Alias_Type");
        final String Alias_Quality = rs.getString("Alias_Quality");
        final String NAME = rs.getString("NAME");
        final Integer Group_ID = rs.getInt("Group_ID");
        return new AliasesDetailsData(Alias_Type, Alias_Quality, NAME, Group_ID);

    }
}
