package com.ponsun.san.sanction.unDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.IdentificationDetailsData;
import com.ponsun.san.sanction.unDetails.data.UnDesignationDetailsData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class UnDesignationDetailsRowMapper implements RowMapper<UnDesignationDetailsData> {
    private final String schema;

    public UnDesignationDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM test_un_sanction ");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" a.identity");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public UnDesignationDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String identity = rs.getString("identity");
        return new UnDesignationDetailsData(identity);
    }
}
