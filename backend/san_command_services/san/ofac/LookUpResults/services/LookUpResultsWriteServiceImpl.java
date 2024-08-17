package com.ponsun.san.ofac.LookUpResults.services;
import com.ponsun.san.ofac.LookUpResults.data.LookUpResultsData;
import com.ponsun.san.ofac.LookUpResults.rowmapper.LookUpResultsRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class LookUpResultsWriteServiceImpl implements LookUpResultsWriteService{
    private final JdbcTemplate jdbcTemplate;
    private final LookUpResultsRowMapper lookUpResultsRowMapper;


    @Override
    public List<LookUpResultsData> fetchAllLookUpResultsData(String name){
        final LookUpResultsRowMapper rowMapper = new LookUpResultsRowMapper();
        String Qry = " SELECT DISTINCT " + rowMapper.tableSchema();
        String whereClause = " WHERE a.PrimaryKey=b.FK_DistinctParty AND b.PrimaryKey=c.FK_Profile AND c.PrimaryKey=d.FK_Identity AND d.PrimaryKey=e.FK_Alias AND e.PrimaryKey=f.FK_DocumentedName AND f.PrimaryKey=g.FK_DocumentedNamePart AND g.`Text` LIKE ? AND g.`ScriptID`=215 GROUP BY FK_DocumentedName";
        Qry = Qry + whereClause;
        final List<LookUpResultsData> lookUpResultsData = jdbcTemplate.query(Qry,new Object[]{"%"+name+"%"},lookUpResultsRowMapper);
        return lookUpResultsData;
    }
}
