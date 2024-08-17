package com.ponsun.san.ofac.Aliases.services;

import com.ponsun.san.ofac.Aliases.data.AliasesData;
import com.ponsun.san.ofac.Aliases.rowmapper.AliasesRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class AliasesWriteServiceImpl implements AliasesWriteService{
    private final JdbcTemplate jdbcTemplate;
    private final AliasesRowMapper aliasesRowMapper;


    @Override
    public List<AliasesData> fetchAllAliasesData(Integer id){
        final AliasesRowMapper rowMapper = new AliasesRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = "WHERE a.PrimaryKey=b.FK_DistinctParty AND b.PrimaryKey=c.FK_Profile AND c.PrimaryKey=d.FK_Identity AND d.PrimaryKey=e.FK_Alias AND e.PrimaryKey=f.FK_DocumentedName AND f.PrimaryKey=g.FK_DocumentedNamePart AND a.FixedRef=? GROUP BY FK_DocumentedName";
        Qry = Qry + whereClause;
        final List<AliasesData> aliasesData = jdbcTemplate.query(Qry,new Object[]{id},aliasesRowMapper);
        return aliasesData;
    }
}