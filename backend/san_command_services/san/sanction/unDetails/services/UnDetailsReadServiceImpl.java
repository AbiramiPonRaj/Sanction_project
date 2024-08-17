package com.ponsun.san.sanction.unDetails.services;

import com.ponsun.san.sanction.unDetails.data.*;
import com.ponsun.san.sanction.unDetails.rowmapper.UnAliasDetailsRowMapper;
import com.ponsun.san.sanction.unDetails.rowmapper.UnDesignationDetailsRowMapper;
import com.ponsun.san.sanction.unDetails.rowmapper.UnPersonalDetailsRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnDetailsReadServiceImpl implements UnDetailsReadService {
    private final JdbcTemplate jdbcTemplate;
    private final UnAliasDetailsRowMapper unAliasDetailsRowMapper;

    @Override
    public List<UnAliasDetailsData> fetchAliasDetails(Integer DATAID) {
        String query = "SELECT 'a.k.a' AS _Type, INDIVIDUAL_ALIAS_0_ALIAS_NAME AS NAME, INDIVIDUAL_ALIAS_0_QUALITY AS QUALITY " +
                "FROM test_un_sanction WHERE INDIVIDUAL_ALIAS_0_ALIAS_NAME != '' AND DATAID = ? " +
                "UNION ALL " +
                "SELECT 'a.k.a' AS _Type, INDIVIDUAL_ALIAS_1_ALIAS_NAME AS NAME, INDIVIDUAL_ALIAS_1_QUALITY AS QUALITY " +
                "FROM test_un_sanction WHERE INDIVIDUAL_ALIAS_1_ALIAS_NAME != '' AND DATAID = ? " +
                "UNION ALL " +
                "SELECT 'a.k.a' AS _Type, INDIVIDUAL_ALIAS_2_ALIAS_NAME AS NAME, INDIVIDUAL_ALIAS_2_QUALITY AS QUALITY " +
                "FROM test_un_sanction WHERE INDIVIDUAL_ALIAS_2_ALIAS_NAME != '' AND DATAID = ? " +
                "UNION ALL " +
                "SELECT 'a.k.a' AS _Type, INDIVIDUAL_ALIAS_3_ALIAS_NAME AS NAME, INDIVIDUAL_ALIAS_3_QUALITY AS QUALITY " +
                "FROM test_un_sanction WHERE INDIVIDUAL_ALIAS_3_ALIAS_NAME != '' AND DATAID = ?";

        return jdbcTemplate.query(query, new Object[]{DATAID, DATAID, DATAID, DATAID}, new UnAliasDetailsRowMapper());
    }

    @Override
    public List<UnDesignationDetailsData> fetchDesignationDetails(int DATAID) {
        String query = "SELECT DESIGNATION_0 AS identity FROM test_un_sanction WHERE DATAID = ? AND DESIGNATION_0 != '' " +
                "UNION ALL " +
                "SELECT DESIGNATION_1 AS identity FROM test_un_sanction WHERE DATAID = ? AND DESIGNATION_1 != '' " +
                "UNION ALL " +
                "SELECT DESIGNATION_2 AS identity FROM test_un_sanction WHERE DATAID = ? AND DESIGNATION_2 != '' " +
                "UNION ALL " +
                "SELECT DESIGNATION_3 AS identity FROM test_un_sanction WHERE DATAID = ? AND DESIGNATION_3 != ''";

        return jdbcTemplate.query(query, new Object[]{DATAID, DATAID, DATAID, DATAID}, new UnDesignationDetailsRowMapper());
    }


    @Override
    public UnPersonalDetailsData fetchAllDetailsData(Integer DATAID) {
        final UnPersonalDetailsRowMapper rowMapper = new UnPersonalDetailsRowMapper();
        String baseQuery = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE DATAID = ?";
        String finalQuery = baseQuery + whereClause;
        UnPersonalDetailsData unPersonalDetailsData = jdbcTemplate.queryForObject(finalQuery, new Object[]{DATAID}, rowMapper);
        return unPersonalDetailsData;
    }

}
