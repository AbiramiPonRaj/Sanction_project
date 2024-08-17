package com.ponsun.san.ofac.Count.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.ponsun.san.ofac.Count.data.SanctionDetailData;
import com.ponsun.san.ofac.Count.rowmapper.EuSanctionRowMapper;
import com.ponsun.san.ofac.Count.rowmapper.UkSanctionRowMapper;
import com.ponsun.san.ofac.Count.rowmapper.UnSanctionRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SanctionDetailReadServiceImpl implements SanctionDetailReadService {
    private final JdbcTemplate jdbcTemplate;
    private final EuSanctionRowMapper euSanctionRowMapper;
    private final UkSanctionRowMapper ukSanctionRowMapper;
    private final UnSanctionRowMapper unSanctionRowMapper;
    private final HazelcastInstance hazelcastInstance;

    private IMap<String, SanctionDetailData> getEuCacheMap() {
        return hazelcastInstance.getMap("EuOfacDataDetail");
    }
    private IMap<String, SanctionDetailData> getUkCacheMap() {
        return hazelcastInstance.getMap("UkOfacDataDetail");
    }
    private IMap<String, SanctionDetailData> getUnCacheMap() {
        return hazelcastInstance.getMap("UNOfacDataDetail");
    }


    @Override
    public SanctionDetailData fetchSanctionData(Integer ids, Integer SanType) {
        SanctionDetailData sanData  = new SanctionDetailData();
        if(SanType==2)
            sanData = getUnCacheMap().get("UN"+ids);
        if(SanType==3)
            sanData = getUkCacheMap().get("UK"+ids);
        if(SanType==4)
            sanData = getEuCacheMap().get("EU"+ids);

        return sanData;

    }

    @Override
    public SanctionDetailData fetchEuSanctionData(Integer entityLogicalId) {
        String Qry = "SELECT " + euSanctionRowMapper.tableSchema();
        String whereClause = " WHERE Entity_logical_id = ? GROUP BY Entity_logical_id";
        Qry = Qry + whereClause;

        return jdbcTemplate.queryForObject(Qry, new Object[]{entityLogicalId}, euSanctionRowMapper);
    }

    @Override
    public SanctionDetailData fetchUkSanctionData(Integer groupId) {
        String Qry = "SELECT " + ukSanctionRowMapper.tableSchema();
        String whereClause = " WHERE Group_id = ? GROUP BY Group_id";
        Qry = Qry + whereClause;

        return jdbcTemplate.queryForObject(Qry, new Object[]{groupId}, ukSanctionRowMapper);
    }

    @Override
    public SanctionDetailData fetchUnSanctionData(Integer dataid) {
        String Qry = "SELECT " + unSanctionRowMapper.tableSchema();
        String whereClause = " WHERE dataid = ?";
        Qry = Qry + whereClause;

        return jdbcTemplate.queryForObject(Qry, new Object[]{dataid}, unSanctionRowMapper);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<SanctionDetailData> fetchAllEUSanctionData() {

        String Qry = "SELECT " + euSanctionRowMapper.tableSchema();
        String whereClause = " GROUP BY Entity_logical_id";
        Qry = Qry + whereClause;
        List<SanctionDetailData> sanctionDetailDataList= jdbcTemplate.query(Qry,euSanctionRowMapper);

        for (SanctionDetailData sanctionDetailData : sanctionDetailDataList)
        {
            IMap<String, SanctionDetailData> cacheMap = getEuCacheMap();
            Integer ids = sanctionDetailData.getIds();
            cacheMap.putIfAbsent("EU"+ids,sanctionDetailData);
        }
        return sanctionDetailDataList;
    }

    public List<SanctionDetailData> fetchAllUKSanctionData() {
        String Qry = "SELECT " + ukSanctionRowMapper.tableSchema();
        String whereClause = "  WHERE Group_id !=' ' GROUP BY Group_id, Group_Type " +
                "HAVING " +
                "    Nationality_country != '' OR " +
                "    Citi_country != '' OR " +
                "    Birt_country != '' OR " +
                "    Iden_country != '' ";
        Qry = Qry + whereClause;
        List<SanctionDetailData> sanctionDetailDataList= jdbcTemplate.query(Qry,ukSanctionRowMapper);

        for (SanctionDetailData sanctionDetailData : sanctionDetailDataList)
        {
            IMap<String, SanctionDetailData> cacheMap = getUkCacheMap();
            Integer ids = sanctionDetailData.getIds();
            cacheMap.putIfAbsent("UK"+ids,sanctionDetailData);
        }
        return sanctionDetailDataList;
    }

    ////////////////////
    public List<SanctionDetailData> fetchAllUnSanctionData() {
        String Qry = "SELECT " + unSanctionRowMapper.tableSchema();
        String whereClause = " group by dataid";
        Qry = Qry + whereClause;
        List<SanctionDetailData> sanctionDetailDataList= jdbcTemplate.query(Qry,unSanctionRowMapper);

        for (SanctionDetailData sanctionDetailData : sanctionDetailDataList)
        {
            IMap<String, SanctionDetailData> cacheMap = getUnCacheMap();
            Integer ids = sanctionDetailData.getIds();
            cacheMap.putIfAbsent("UN"+ids,sanctionDetailData);
        }
        return sanctionDetailDataList;
    }



}

//    @Override
//    public Map<String, List<SanctionDetailData>> fetchSanctionData(Integer entityLogicalId, Integer groupId, Integer dataId) {
//        Map<String, List<SanctionDetailData>> result = new HashMap<>();
//
////        if (entityLogicalId != null) {
////            result.put("EU", fetchEuSanctionData(entityLogicalId));
////        }
//
//        if (groupId != null) {
//            result.put("UK", fetchUkSanctionData(groupId));
//        }
//
//        if (dataId != null) {
//            result.put("UN", fetchUnSanctionData(dataId));
//        }
//
//        return result;
//    }
//
//
//
//    @Override
//    public SanctionDetailData fetchEuSanctionData(Integer entityLogicalId) {
//
////        final EuSanctionRowMapper rowMapper = new EuSanctionRowMapper();
//        String Qry = "SELECT " + euSanctionRowMapper.tableSchema();
//        String whereClause = " WHERE Entity_logical_id= ? GROUP BY Entity_logical_id";
//        Qry = Qry + whereClause ;
////        final SanctionDetailData euData = jdbcTemplate.query(Qry,new Object[]{entityLogicalId}, euSanctionRowMapper);
//        SanctionDetailData euData = new SanctionDetailData();
//        try {
//            // Use queryForObject if expecting a single result
//            euData = jdbcTemplate.queryForObject(Qry, new Object[]{entityLogicalId}, euSanctionRowMapper);
//        } catch (EmptyResultDataAccessException e) {
//            // Handle case where no results are found
//            System.out.println("No data found for Entity_logical_id: " + entityLogicalId);
//        } catch (IncorrectResultSizeDataAccessException e) {
//            // Handle case where more than one result is found
//            System.out.println("Multiple results found for Entity_logical_id: " + entityLogicalId);
//        }
//        return euData;
//    }
//
//    @Override
//    public List<SanctionDetailData> fetchUkSanctionData(Integer Group_id) {
//        final UkSanctionRowMapper rowMapper = new UkSanctionRowMapper();
//        String Qry = "SELECT " + rowMapper.tableSchema();
//        String whereClause = " WHERE Group_id=? GROUP BY Group_id";
//        Qry = Qry + whereClause ;
//        final List<SanctionDetailData> ukData = jdbcTemplate.query(Qry,new Object[]{Group_id}, ukSanctionRowMapper);
//        return ukData;
//    }
//
//    @Override
//    public List<SanctionDetailData> fetchUnSanctionData(Integer dataid) {
//        final UnSanctionRowMapper rowMapper = new UnSanctionRowMapper();
//        String Qry = "SELECT " + rowMapper.tableSchema();
//        String whereClause = " WHERE dataid=?";
//        Qry = Qry + whereClause ;
//        final List<SanctionDetailData> unData = jdbcTemplate.query(Qry,new Object[]{dataid}, unSanctionRowMapper);
//        return unData;
//    }
//}
