package com.ponsun.san.ofac.identification.services;
import com.ponsun.san.ofac.identification.data.IdentificationData;
import com.ponsun.san.ofac.identification.rowmapper.IdentificationRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdentificationWriteServiceImpl implements IdentificationWriteService{
    private final JdbcTemplate jdbcTemplate;
    private final IdentificationRowMapper identificationRowMapper;


    @Override
    public List<IdentificationData> fetchAllIdentificationData(String id){
        final IdentificationRowMapper rowMapper = new IdentificationRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE f.`ID`=a.`IdentityID` AND f.`FixedRef`=?";
        Qry = Qry + whereClause;
        final List<IdentificationData> identificationData = jdbcTemplate.query(Qry,new Object[]{id},identificationRowMapper);
        return identificationData;
    }

}