package com.ponsun.san.EhcachePOC.Service;

import com.ponsun.san.EhcachePOC.Data.RecordDetailData;
import com.ponsun.san.EhcachePOC.RowMapper.OFACIdentificationRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
//@CacheConfig(cacheNames = "OfacDataDetail")
public class OFACDetailServiceImpl implements OFACDetailService{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<RecordDetailData> fetchTestingData() {
        final OFACIdentificationRowMapper identificationRowMapper = new OFACIdentificationRowMapper();
        String Qry = "SELECT " + identificationRowMapper.tableSchema();
        String whereClause = " WHERE f.`ID`=a.`IdentityID` GROUP BY f.`FixedRef`";
        Qry = Qry + whereClause;
        final List<RecordDetailData> detailData = jdbcTemplate.query(Qry, identificationRowMapper);

        List<RecordDetailData> tempData = new ArrayList<>();
        for(RecordDetailData detailData1 : detailData)
        {
//            String heading = detailData1.getHeading();
//            String val = detailData1.getVal();
            tempData = fetchTestingDataById(detailData1.getId());
//            entityCache.put(detailData1.getId(),new RecordDetailData(detailData1.getId(),heading,val));
        }

        return tempData;
    }
    @Override
//    @Cacheable(value = "OfacDataDetail", key = "#id")
    public List<RecordDetailData> fetchTestingDataById(Integer id) {
        final OFACIdentificationRowMapper identificationRowMapper = new OFACIdentificationRowMapper();
        String Qry = "SELECT " + identificationRowMapper.tableSchema();
        String whereClause = " WHERE f.`ID`=a.`IdentityID` and f.FixedRef= ? GROUP BY f.`FixedRef`";

        Qry = Qry + whereClause;
        final List<RecordDetailData> detailData = jdbcTemplate.query(Qry,new Object[]{id}, identificationRowMapper);

        return detailData;
    }
}
