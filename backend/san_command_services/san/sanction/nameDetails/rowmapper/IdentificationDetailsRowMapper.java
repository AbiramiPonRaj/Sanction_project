package com.ponsun.san.sanction.nameDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.IdentificationDetailsData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class IdentificationDetailsRowMapper implements RowMapper<IdentificationDetailsData> {
    private final String schema;

    public IdentificationDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_eu_sanction a");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" a.Iden_leba_numtitle,a.Iden_number,a.Iden_country,a.Iden_Leba_publication_date , a.Entity_logical_id_Iden");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public IdentificationDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String iden_leba_numtitle = rs.getString("Iden_leba_numtitle");
        final String iden_number = rs.getString("Iden_number");
        final String iden_country = rs.getString("Iden_country");
        final String iden_Leba_publication_date = rs.getString("Iden_Leba_publication_date");
        final Integer entity_logical_id_Iden = rs.getInt("Entity_logical_id_Iden");
        return new IdentificationDetailsData(iden_leba_numtitle, iden_number, iden_country, iden_Leba_publication_date, entity_logical_id_Iden);
    }
}
