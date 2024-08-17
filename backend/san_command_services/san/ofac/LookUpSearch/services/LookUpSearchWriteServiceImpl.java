package com.ponsun.san.ofac.LookUpSearch.services;
import com.ponsun.san.ofac.LookUpSearch.data.LookUpSearchData;
import com.ponsun.san.ofac.LookUpSearch.rowmapper.LookUpSearchRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LookUpSearchWriteServiceImpl implements LookUpSearchWriteService {
    private final JdbcTemplate jdbcTemplate;
    private final LookUpSearchRowMapper lookUpSearchRowMapper;

    @Override
    public List<LookUpSearchData> fetchAllLookUpSearchData(){
        final LookUpSearchRowMapper rowMapper = new LookUpSearchRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE b.`FK_Identity` = a.`PrimaryKey` AND c.`FK_Alias` = b.`PrimaryKey` AND d.`FK_DocumentedName` = c.`PrimaryKey` AND e.`FK_DocumentedNamePart`=d.`PrimaryKey`";
        Qry = Qry + whereClause;
        final List<LookUpSearchData> lookUpSearchData = jdbcTemplate.query(Qry,lookUpSearchRowMapper);
        return lookUpSearchData;
    }

}
