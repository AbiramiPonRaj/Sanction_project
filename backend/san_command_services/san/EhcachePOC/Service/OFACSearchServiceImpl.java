package com.ponsun.san.EhcachePOC.Service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.ponsun.san.EhcachePOC.Data.OFACData;
import com.ponsun.san.EhcachePOC.RowMapper.OFACRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@CacheConfig(cacheNames = "allOfacData")
@Service
@Slf4j
public class OFACSearchServiceImpl implements OFACSearchService{
    private final JdbcTemplate jdbcTemplate;
    private final HazelcastInstance hazelcastInstance;

    private IMap<Integer, List<OFACData>> getCacheMap() {
        return hazelcastInstance.getMap("allOfacData");
    }

    @Override
    public List<OFACData> fetchAllOFACData() {

        ///////////////////////////
        IMap<Integer, List<OFACData>> cacheMap1 = getCacheMap();
        List<OFACData> mapRet1 = cacheMap1.get(1);  // Retrieve the cached data using the key
        System.out.println("Retrieved from cache: " + mapRet1);

        if (mapRet1 != null && !mapRet1.isEmpty()) {
            return mapRet1;
        }
        /////////////////////////////
        log.info("Data retrieved from database");
        final OFACRowMapper rowMapper = new OFACRowMapper();
        String qry = "SELECT " + rowMapper.tableSchema();
        List<OFACData> ofacData = jdbcTemplate.query(qry, rowMapper);

        IMap<Integer, List<OFACData>> cacheMap = getCacheMap();
        cacheMap.put(1, ofacData);


//        for(OFACData ofacDatat : ofacData) {
//            Integer key = ofacDatat.getId();
//            // Cache the data in Hazelcast
//            IMap<Integer, OFACData> cacheMap = getCacheMap();
//            cacheMap.put(key, ofacDatat);
//        }

        log.info("Data saved into Hazelcast cache");
        return ofacData;
    }

    @Override
    public String deleteOfacData() {
        log.info("OfacData deleted from cache");
        IMap<Integer, List<OFACData>> cacheMap = getCacheMap();
        cacheMap.clear(); // Clear the entire cache
        return "Successfully removed data from cache";
    }


    public List<OFACData> getCachedData(Integer key) {
        IMap<Integer, List<OFACData>> cacheMap = getCacheMap();
        // Retrieve the cached data using the key
        List<OFACData> cachedData = cacheMap.get(key);
        if (cachedData instanceof List) {
            return cachedData;
        }
        return cacheMap.get(key); // Return an empty list if no data is found
    }




//    @Cacheable(value = "allOfacData")
//    @Override
//    public List<OFACData> fetchAllOFACData() {
//        System.out.println("Data retrieved from database");
//        final OFACRowMapper rowMapper = new OFACRowMapper();
//        String Qry = "SELECT " + rowMapper.tableSchema();
//        final List<OFACData> ofacData = jdbcTemplate.query(Qry,rowMapper);
//        System.out.println("Data Saved into Cacheable allOfacData from database");
////        System.out.println("ofacData"+ofacData);
//        return ofacData;
//    }
//    @Caching(evict = {
//            @CacheEvict(value = "allOfacData", allEntries = true)
//    })
//    @Override
//    public String deleteOfacData() {
//        System.out.println("OfacData deleted in Caching");
//        return "Successfully Data removed from Cache";
//    }

}
