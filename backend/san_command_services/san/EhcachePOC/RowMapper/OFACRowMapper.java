package com.ponsun.san.EhcachePOC.RowMapper;

import com.ponsun.san.EhcachePOC.Data.OFACData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class OFACRowMapper implements RowMapper<OFACData> {
    private final String schema;

    public OFACRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM (SELECT id, " +
                " CONCAT(SUBSTRING_INDEX(NAME, ',', 1),',',TRIM(REPLACE(REPLACE(NAME, " +
                " SUBSTRING_INDEX(NAME, ',', 1),''),',',' '))) AS NAME, " +
                " 1 AS fileType,'US OFAC' AS _list" +
                " FROM (SELECT id, " +
                " GROUP_CONCAT(NAME) AS NAME " +
                " FROM  ( SELECT a.FixedRef AS id, g.Text AS NAME,FK_DocumentedName " +
                " FROM distinctparty a,PROFILE b LEFT JOIN sanctionctionsentry b1 ON b.ID=b1.ProfileID,identity c,alias d,documentedname e,documentednamepart f,namepartvalue g,namepartgroup h,nameparttype i " +
                " WHERE a.PrimaryKey=b.FK_DistinctParty AND b.PrimaryKey=c.FK_Profile AND c.PrimaryKey=d.FK_Identity AND d.PrimaryKey=e.FK_Alias AND g.ScriptID=215 " +
                " AND e.PrimaryKey=f.FK_DocumentedName AND f.PrimaryKey=g.FK_DocumentedNamePart  AND g.NamePartGroupID=h.ID AND h.NamePartTypeID=i.ID  " +
                " ORDER BY FK_DocumentedName,i.ID) a GROUP BY FK_DocumentedName ) b  " +
                " UNION" +
                " SELECT * FROM (SELECT Entity_logical_id AS id,Naal_wholename  AS NAME,2 AS fileType,'EU LIST' AS _list" +
                " FROM test_eu_sanction WHERE Naal_wholename IS NOT NULL AND Naal_wholename != '' AND Naal_language='EN' GROUP BY Naal_wholename) a" +
                " UNION" +
                " SELECT * FROM (SELECT Group_ID AS id," +
                " CONCAT(name_1,IF(LENGTH(name_1)=0,'',' ')," +
                " name_2,IF(LENGTH(name_2)=0,'',' ')," +
                " name_3,IF(LENGTH(name_3)=0,'',' ')," +
                " name_4,IF(LENGTH(name_4)=0,'',' ')," +
                " name_5,IF(LENGTH(name_5)=0,'',' ')," +
                " name_6" +
                " ) AS NAME," +
                " 3 AS fileType,'UK HMT' AS _list" +
                " FROM `test_uk_sanction` GROUP BY 2) c  WHERE c.name !=''" +
                " UNION" +
                " SELECT * FROM (SELECT DATAID AS id,CONCAT(FIRST_NAME,' ',SECOND_NAME,' ',THIRD_NAME) AS NAME,4 AS fileType,'UN LIST' AS _list FROM `test_un_sanction`" +
                " UNION ALL" +
                " SELECT DATAID AS id,INDIVIDUAL_ALIAS_0_ALIAS_NAME AS NAME,4 AS fileType,'UN LIST' AS _list FROM `test_un_sanction`  WHERE INDIVIDUAL_ALIAS_0_ALIAS_NAME !=''" +
                " UNION ALL" +
                " SELECT DATAID AS id,INDIVIDUAL_ALIAS_1_ALIAS_NAME AS NAME,4 AS fileType,'UN LIST' AS _list FROM `test_un_sanction` WHERE INDIVIDUAL_ALIAS_1_ALIAS_NAME!=''" +
                " UNION ALL" +
                " SELECT DATAID AS id,INDIVIDUAL_ALIAS_2_ALIAS_NAME AS NAME,4 AS fileType,'UN LIST' AS _list FROM `test_un_sanction` WHERE INDIVIDUAL_ALIAS_2_ALIAS_NAME !=''" +
                " ) D" +
                " ) a  ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("id AS id,name AS Name,fileType AS fileType,REGEXP_REPLACE(NAME, '[^a-zA-Z0-9]', ' '),_list AS _list");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public OFACData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final String name = rs.getString("Name");
        final String orgName= "";
        final Integer fileType=rs.getInt("fileType");
        final String fileList   = rs.getString("_list");
        return new OFACData(id,name,orgName,fileType,fileList);
    }

}

