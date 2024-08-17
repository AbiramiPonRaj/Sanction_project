package com.ponsun.san.EhcachePOC.RowMapper;

import com.ponsun.san.EhcachePOC.Data.RecordDetailData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class OFACIdentificationRowMapper implements RowMapper<RecordDetailData> {
    private final String schema;

    public OFACIdentificationRowMapper() {
        final StringBuilder builder = new StringBuilder(400);
        builder.append(" FROM IDRegDocument a" +
                " LEFT OUTER JOIN  `documentdate` b ON a.`PrimaryKey`=b.`FK_IDRegDocument`" +
                " LEFT JOIN dateperiod c ON  c.`FK_DocumentDate`=b.`PrimaryKey`" +
                " LEFT JOIN `start` d ON d.`FK_DatePeriod`=c.`PrimaryKey`" +
                " LEFT JOIN `from` e ON e.`FK_Start`=d.`PrimaryKey` ,identity f");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" f.`FixedRef` AS keyId,'Identifications' AS heading ,GROUP_CONCAT(DISTINCT (SELECT TEXT FROM country x1 WHERE  a.`IssuedBy-CountryID`=x1.`ID`)) AS val");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public RecordDetailData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("keyId");
        final String heading = rs.getString("heading");
        final String val = rs.getString("val");
        return new RecordDetailData(id,heading,val);

    }
}
