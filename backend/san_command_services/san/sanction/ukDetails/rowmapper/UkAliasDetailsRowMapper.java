package com.ponsun.san.sanction.ukDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.AddressFileData;
import com.ponsun.san.sanction.ukDetails.data.UkAliasDetailsData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class UkAliasDetailsRowMapper implements RowMapper<UkAliasDetailsData> {
    private final String schema;

    public UkAliasDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_uk_sanction ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" CONCAT(name_1,' ',name_2,' ',name_3,' ',name_4,' ',name_5,'',name_6) AS NAME , Group_ID AS Group_ID , Alias_Type AS Alias_Type ");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public UkAliasDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String name = rs.getString("name");
        final Integer Group_ID = rs.getInt("Group_ID");
        final String Alias_Type = rs.getString("Alias_Type");
        return new UkAliasDetailsData(name, Group_ID, Alias_Type);
    }
}
