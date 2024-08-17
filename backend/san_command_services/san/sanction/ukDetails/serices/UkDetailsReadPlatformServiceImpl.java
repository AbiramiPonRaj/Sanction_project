package com.ponsun.san.sanction.ukDetails.serices;

import com.ponsun.san.sanction.ukDetails.data.UkAliasDetailsData;
import com.ponsun.san.sanction.ukDetails.data.UkCityDetailsData;
import com.ponsun.san.sanction.ukDetails.rowmapper.UkAliasDetailsRowMapper;
import com.ponsun.san.sanction.ukDetails.rowmapper.UkCityDetailsRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UkDetailsReadPlatformServiceImpl implements  UkDetailsReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final UkAliasDetailsRowMapper ukAliasDetailsRowMapper;
    private final UkCityDetailsRowMapper ukCityDetailsRowMapper;

    @Override
    public List<UkAliasDetailsData> fetchAllUkAliasDetailsData(Integer Group_ID) {
        final UkAliasDetailsRowMapper rowMapper = new UkAliasDetailsRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE Group_ID=? AND Alias_Type= 'Primary name' GROUP BY 1";
        Qry = Qry + whereClause;
        final List<UkAliasDetailsData> ukAliasDetailsDataList = jdbcTemplate.query(Qry, rowMapper,
                new Object[]{Group_ID}
        );
        return ukAliasDetailsDataList;
    }

    @Override
    public List<UkCityDetailsData> fetchAllUkCityDetailsData(Integer Group_ID) {
        final UkCityDetailsRowMapper rowMapper = new UkCityDetailsRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE Group_ID=? LIMIT 1";
        Qry = Qry + whereClause;
        final List<UkCityDetailsData> ukCityDetailsDataList = jdbcTemplate.query(Qry, rowMapper,
                new Object[]{Group_ID}
        );
        return ukCityDetailsDataList;
    }
}
