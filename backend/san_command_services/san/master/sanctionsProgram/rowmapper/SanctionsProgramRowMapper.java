package com.ponsun.san.master.sanctionsProgram.rowmapper;





import com.ponsun.san.master.sanctionsProgram.data.SanctionsProgramData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@Service
@Slf4j
public class SanctionsProgramRowMapper implements RowMapper<SanctionsProgramData> {
    private final String schema;
    public SanctionsProgramRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM sanctionsprogram sp ");
        this.schema = builder.toString();
    }
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("sp.PrimaryKey as PrimaryKey, ");
        builder.append("sp.ID as ID , ");
        builder.append("sp.SubsidiaryBodyID as SubsidiaryBodyID , ");
        builder.append("sp.Text as Text, ");
        builder.append("sp.FK_SanctionsProgramValues as FK_SanctionsProgramValues ");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public SanctionsProgramData mapRow(ResultSet rs, int rowNum) throws SQLException{
        final String PrimaryKey = rs.getString("PrimaryKey");
        final String ID = rs.getString("ID");
        final String SubsidiaryBodyID = rs.getString("SubsidiaryBodyID");
        final String  Text = rs.getString("Text");
        final String FK_SanctionsProgramValues = rs.getString("FK_SanctionsProgramValues");
        return new SanctionsProgramData(PrimaryKey,ID,SubsidiaryBodyID,Text,FK_SanctionsProgramValues);
    }
}
