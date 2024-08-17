package com.ponsun.san.ofac.Details.rowmapper;
import com.ponsun.san.ofac.Details.data.DetailsData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class DetailsRowMapper implements RowMapper<DetailsData> {
    private final String schema;

    public DetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM (" +
                " SELECT * FROM (SELECT i.`Text` AS heading,g.`Text` AS val FROM alias d,documentedname e,documentednamepart f,namepartvalue g,`namepartgroup` h,`nameparttype` i" +
                " WHERE d.PrimaryKey=e.FK_Alias AND e.PrimaryKey=f.FK_DocumentedName AND f.PrimaryKey=g.FK_DocumentedNamePart AND g.`NamePartGroupID`=h.`ID` " +
                " AND h.`NamePartTypeID`=i.`ID` " +
                " AND e.`FixedRef` =? and g.ScriptID=215 AND d.`Primary`='true' ORDER BY 1) a" +
                " UNION" +
                " SELECT e.`Text` AS heading,GROUP_CONCAT(a.`Text`,'')  AS val" +
                " FROM versiondetail a,featureversion b,feature c,`profile` d,featuretype e" +
                " WHERE a.`FK_FeatureVersion`=b.`PrimaryKey` AND b.`FK_Feature`=c.`PrimaryKey` AND c.`FK_Profile`=d.`PrimaryKey` " +
                " AND e.`ID`=c.`FeatureTypeID` AND a.`Text` IS NOT NULL" +
                " AND d.`ID`=? GROUP BY 1" +
                " UNION" +
                " SELECT heading,GROUP_CONCAT(val)  AS val FROM " +
                " (SELECT h.`Text` AS heading,IF(g.`Month` = 1 AND g.`Day` = 1, g.`Year`, DATE_FORMAT(CONCAT(g.`Year`, '-', g.`Month`, '-', g.`Day`), '%d %M %Y')) AS val  " +
                " FROM versiondetail a,featureversion b,feature c,`profile` d,`dateperiod` e," +
                " `start` f,`from` g,`featuretype` h" +
                " WHERE a.`FK_FeatureVersion`=b.`PrimaryKey` AND b.`FK_Feature`=c.`PrimaryKey` AND c.`FK_Profile`=d.`PrimaryKey` " +
                " AND e.`FK_FeatureVersion`=b.`PrimaryKey` AND e.`PrimaryKey`=f.`FK_DatePeriod`" +
                " AND g.`FK_Start`=f.`PrimaryKey` AND h.`ID`=c.`FeatureTypeID`" +
                " AND d.`ID`=?) a GROUP BY heading" +
                " UNION" +
                " SELECT e.`Text` AS heading,GROUP_CONCAT(i.`Value`) AS val" +
                " FROM versiondetail a,featureversion b,feature c,`profile` d,featuretype e," +
                " `versionlocation` f,`location` g,`locationpart` h,`locationpartvalue` i" +
                " WHERE a.`FK_FeatureVersion`=b.`PrimaryKey` AND b.`FK_Feature`=c.`PrimaryKey` AND c.`FK_Profile`=d.`PrimaryKey` " +
                " AND e.`ID`=c.`FeatureTypeID` AND h.`FK_Location`=g.`PrimaryKey` AND i.`FK_LocationPart`=h.`PrimaryKey`" +
                " AND f.`FK_FeatureVersion`=b.`PrimaryKey` AND f.`LocationID`=g.`ID` " +
                " AND d.`ID`=? GROUP BY 1" +
                " UNION" +
                " SELECT e.`Text` AS heading,g.`Text` AS val" +
                " FROM versiondetail a,featureversion b,feature c,`profile` d,featuretype e,versiondetail f,`detailreference` g" +
                " WHERE a.`FK_FeatureVersion`=b.`PrimaryKey` AND b.`FK_Feature`=c.`PrimaryKey` AND c.`FK_Profile`=d.`PrimaryKey` " +
                " AND e.`ID`=c.`FeatureTypeID` AND f.`FK_FeatureVersion`=b.`PrimaryKey` AND f.`DetailReferenceID`=g.`ID`" +
                " AND d.`ID`=? UNION " +
                " SELECT 'Remark' AS heading, a.`Comment` AS val FROM distinctparty a WHERE a.`Comment` IS NOT NULL AND a.`FixedRef` = ?"+
                " UNION" +
                " SELECT c.`Text` AS heading,GROUP_CONCAT(b.`Comment`) AS val " +
                " FROM `sanctionsentry` a,`sanctionsmeasure` b,`sanctionstype` c" +
                " WHERE a.`PrimaryKey`=b.`FK_SanctionsEntry` AND b.`SanctionsTypeID`=c.`ID`" +
                " AND b.`Comment` IS NOT NULL AND a.`ProfileID`=? GROUP BY 1" +
                " UNION" +
                " SELECT 'List',c.`Text` FROM `profile` a,`sanctionsentry` b,`list` c" +
                " WHERE a.`ID`=b.ProfileID AND b.`ListID`=c.`ID` AND a.`ID`=?" +
                " UNION" +
                " SELECT 'type' AS heading,(SELECT IF(b.Text='Unknown',a.Text,b.Text) FROM partytype a,partysubtype b " + "WHERE a.ID=b.PartyTypeID AND b.`ID`=PartySubTypeID)AS val" +
                " FROM PROFILE a " +
                " WHERE a.`ID`=?" +
                " UNION" +
                " SELECT heading,val FROM (SELECT 'Remarks:' AS heading,GROUP_CONCAT(vl) AS val,1 FROM ( " +
                " SELECT CONCAT('Linked To: ',g.`Text`) AS vl,1 FROM identity c,alias d,documentedname e,documentednamepart f,namepartvalue g,profilerelationship h " +
                " WHERE d.`FK_Identity`=c.`PrimaryKey` AND e.`FK_Alias`=d.`PrimaryKey` AND f.`FK_DocumentedName`=e.`PrimaryKey` AND g.`FK_DocumentedNamePart`=f.`PrimaryKey` " +
                " AND h.`To-ProfileID`=c.`FixedRef` AND h.`From-ProfileID`=? AND d.`Primary`='true') a GROUP BY 3) b"+
                " )");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);

        builder.append("heading,val");
        //builder.append("pcal. value as value");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public DetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String heading = rs.getString("heading");
        final String val = rs.getString("val");
        return new DetailsData(heading, val);
    }
}
