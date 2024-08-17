package com.ponsun.san.master.all.services;

import com.ponsun.san.master.all.data.AllData;
import com.ponsun.san.master.all.rowmapper.AllRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AllReadPlatformServiceImpl implements AllReadPlatformService {

    private final AllRowMapper allRowMapper;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<AllData> fetchAllAllData() {
        final AllRowMapper rowMapper = new AllRowMapper();
        String query = "SELECT " + rowMapper.tableSchema() + "  WHERE a.ID = b.PartyTypeID";
        return jdbcTemplate.query(query, rowMapper);
    }

}
