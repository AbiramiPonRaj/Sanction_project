package com.ponsun.san.searchLifcycle.hitrecord.rowmapper;

import com.ponsun.san.searchLifcycle.hitrecord.data.HitRecordData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class HitRecordRowMapper implements RowMapper<HitRecordData> {
    private final String schema;
    public HitRecordRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM hitrecord hit ");
        this.schema = builder.toString();
    }

    public String schema() {
        return this.schema;
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);

        builder.append("hit.id as id, ");
        builder.append("hit.searchId as searchId, ");
        builder.append("hit.display as display, ");
        builder.append("hit.criminalId as criminalId, ");
        builder.append("hit.matchingScore as matchingScore, ");
        builder.append("hit.name as name, ");
        builder.append("hit.statusNowId as statusNowId, ");
        builder.append("hit.cycleId as cycleId, ");
        builder.append("hit.uid as uid, ");
        builder.append("hit.valid as valid ");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public HitRecordData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final Integer searchId = rs.getInt("searchId");
        final String display = rs.getString("display");
        final Integer criminalId = rs.getInt("criminalId");
        final Double matchingScore = rs.getDouble("matchingScore");
        final String name = rs.getString("name");
        final Integer statusNowId = rs.getInt("statusNowId");
        final Integer cycleId = rs.getInt("cycleId");
        final Long uid = rs.getLong("uid");
        final Boolean valid = rs.getBoolean("valid");

        return HitRecordData.newInstance(id, searchId, display, criminalId, matchingScore, name, statusNowId, cycleId, uid, valid);

    }

}
