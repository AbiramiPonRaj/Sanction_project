//package com.ponsun.san.ofac.NamePartValue.rowmapper;
//
//import com.ponsun.san.ofac.NamePartValue.data.NamePartValueData;
//import lombok.Data;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Service;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Data
//@Service
//public class NamePartValueRowMapper implements RowMapper<NamePartValueData> {
//    private final String schema;
//
//    public NamePartValueRowMapper() {
//        final StringBuilder builder = new StringBuilder(200);
//        builder.append("FROM name_part_value pcal ");
//        this.schema = builder.toString();
//    }
//
//    public String tableSchema() {
//        final StringBuilder builder = new StringBuilder(200);
//        builder.append("pcal.id as id");
//        builder.append("pcal.PrimaryKey as PrimaryKey");
//        builder.append("pcal.NamePartGroupID as NamePartGroupID");
//        builder.append("pcal.ScriptID as ScriptID");
//        builder.append("pcal.ScriptStatusID as ScriptStatusID");
//        builder.append("pcal.Acronym as Acronym");
//        builder.append("pcal.Text as Text");
//        builder.append("pcal.FK_DocumentedNamePart as FK_DocumentedNamePart");
//        builder.append(this.schema);
//        return builder.toString();
//    }
//    @Override
//    public NamePartValueData mapRow(ResultSet rs, int rowNum) throws SQLException {
//        final Integer id = rs.getInt("id");
//        final String PrimaryKey = rs.getString("PrimaryKey");
//        final String NamePartGroupID = rs.getString("NamePartGroupID");
//        final String ScriptID = rs.getString("ScriptID");
//        final String ScriptStatusID = rs.getString("ScriptStatusID");
//        final String Acronym = rs.getString("Acronym");
//        final String Text = rs.getString("Text");
//        final String FK_DocumentedNamePart = rs.getString("FK_DocumentedNamePart");
//        return new NamePartValueData(id,PrimaryKey,NamePartGroupID,ScriptID,ScriptStatusID,Acronym,Text,FK_DocumentedNamePart);
//
//    }
//}
