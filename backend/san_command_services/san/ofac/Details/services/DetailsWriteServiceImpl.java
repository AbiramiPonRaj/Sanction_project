package com.ponsun.san.ofac.Details.services;
import com.ponsun.san.ofac.Details.data.DetailsData;
import com.ponsun.san.ofac.Details.rowmapper.DetailsRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class DetailsWriteServiceImpl implements DetailsWriteService {
    private final JdbcTemplate jdbcTemplate;
    private final DetailsRowMapper lookUpRowMapper;

    @Override
    public List<DetailsData> fetchAllDetailsData(Integer id){
        final DetailsRowMapper rowMapper = new DetailsRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " a";
        Qry = Qry + whereClause;
        final List<DetailsData> detailsData = jdbcTemplate.query(Qry, new Object[]{id,id,id,id,id,id,id,id,id,id} ,lookUpRowMapper);
        return detailsData;
    }
}
