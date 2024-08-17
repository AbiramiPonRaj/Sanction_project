package com.ponsun.san.ofac.liststatus.services;

import com.ponsun.san.ofac.Program.rowmapper.ProgramRowMapper;
import com.ponsun.san.ofac.liststatus.data.ListStatusData;
import com.ponsun.san.ofac.liststatus.rowmapper.ListStatusRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListStatusWriteServiceImpl implements ListStatusWriteService {
    private final JdbcTemplate jdbcTemplate;
    private final ListStatusRowMapper listStatusRowMapper;

    @Override
    public List<ListStatusData> fetchAlllistStatus(String id) {
        final ProgramRowMapper rowMapper = new ProgramRowMapper();
        String sql = "SELECT liststatus(?) as liststatus";

        final List<ListStatusData> listStatusData = jdbcTemplate.query(sql, new Object[]{id}, listStatusRowMapper);

        return listStatusData;
    }

}


