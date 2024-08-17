package com.ponsun.san.ofac.lookup.services;
import com.ponsun.san.ofac.lookup.data.LookUpData;
import com.ponsun.san.ofac.lookup.rowmapper.LookUpRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class LookupWriteServiceImpl implements LookupWriteService {
    private final JdbcTemplate jdbcTemplate;
    private final LookUpRowMapper lookUpRowMapper;


    @Override
    public List<LookUpData> fetchAllLookUpData(){
        final LookUpRowMapper rowMapper = new LookUpRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE a.assignTo=b.id AND c.id=a.stateId AND d.id=a.countryId";
        Qry = Qry + whereClause;
        final List<LookUpData> lookUpData = jdbcTemplate.query(Qry,lookUpRowMapper);
        return lookUpData;
    }
}
