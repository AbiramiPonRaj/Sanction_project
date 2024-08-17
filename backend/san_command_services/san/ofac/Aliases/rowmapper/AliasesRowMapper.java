package com.ponsun.san.ofac.Aliases.rowmapper;
import com.ponsun.san.ofac.Aliases.data.AliasesData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class AliasesRowMapper implements RowMapper<AliasesData> {
    private final String schema;
    public AliasesRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM distinctparty a,PROFILE b,identity c,alias d,documentedname e,documentednamepart f,namepartvalue g ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);

        builder.append("(SELECT h.text FROM aliastype h WHERE d.AliasTypeID=h.`ID`) AS AliasesType, IF(d.`LowQuality`='true','Week','Strong') AS Category,GROUP_CONCAT(g.Text) AS AliasesName");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public AliasesData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String AliasesType = rs.getString("AliasesType");
        final String Category = rs.getString("Category");   //Quality
        final String AliasesName = rs.getString("AliasesName");
        return new AliasesData(AliasesType,Category, AliasesName);

    }
}