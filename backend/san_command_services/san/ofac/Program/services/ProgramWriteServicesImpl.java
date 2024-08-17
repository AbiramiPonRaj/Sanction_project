

package com.ponsun.san.ofac.Program.services;

import com.ponsun.san.ofac.Program.data.ProgramData;
import com.ponsun.san.ofac.Program.rowmapper.ProgramRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgramWriteServicesImpl implements ProgramWriteService {
    private final JdbcTemplate jdbcTemplate;
    private final ProgramRowMapper programRowMapper;

    @Override
    public List<ProgramData> fetchAllProgramData(String id) {
        final ProgramRowMapper rowMapper = new ProgramRowMapper();
        String sql = "SELECT Program(?) as Program";

        final List<ProgramData> programData = jdbcTemplate.query(sql, new Object[]{id}, programRowMapper);

        return programData;
    }

}


