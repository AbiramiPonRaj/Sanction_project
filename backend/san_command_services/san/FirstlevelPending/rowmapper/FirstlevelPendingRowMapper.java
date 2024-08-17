package com.ponsun.san.FirstlevelPending.rowmapper;
import com.ponsun.san.FirstlevelPending.data.FirstlevelPendingData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class FirstlevelPendingRowMapper implements RowMapper<FirstlevelPendingData> {

    private final String schema;

    public FirstlevelPendingRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM ( " +
                "SELECT a.name, a.matching_score, b.name AS hitName, b.matchingScore AS hitScore, b.id AS hitId, " +
                "a.id AS searchId, f.search_id AS lifcycleSearchId, b.criminalId AS recId, b.fileType AS fileType " +
                "FROM search a, hitrecord b " +
                "LEFT JOIN hitrecord_lifecycle f ON b.id = f.hitdata_id AND f.level_id = 1 " +
                "WHERE a.id = b.searchId AND a.id=?) a WHERE lifcycleSearchId IS NULL ORDER BY hitScore DESC");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("SELECT *");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public FirstlevelPendingData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String name = rs.getString("name");
        final Double matching_score = rs.getDouble("matching_score");
        final String hitName = rs.getString("hitName");
        final Double hitScore = rs.getDouble("hitScore");
        final Integer hitId = rs.getInt("hitId");
        final Integer searchId = rs.getInt("searchId");
        final Integer lifcycleSearchId = rs.getInt("lifcycleSearchId");
        final Integer recId = rs.getInt("recId");
        final Integer fileType = rs.getInt("fileType");
        return new FirstlevelPendingData(name, matching_score, hitName, hitScore, hitId, searchId, lifcycleSearchId, recId, fileType);
    }
}
