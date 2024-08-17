package com.ponsun.san.ofac.Count.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import com.hazelcast.core.HazelcastInstance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.hazelcast.map.IMap;

import static java.time.LocalTime.now;

@Component
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "OfacDataDetail")
public class RecordDetails {

    private final JdbcTemplate jdbcTemplate;

    private final HazelcastInstance hazelcastInstance;
    private IMap<Integer, HashMap<String, String>> getCacheMap() {
        return hazelcastInstance.getMap("OfacDataDetail");
    }
//
//    @Async("asyncExecutor")
//    public Future<String> getAddress(Integer id) {
//        return CompletableFuture.supplyAsync(() -> {
//            String records = "";
//            try {
//                String sql = "SELECT VALUE as typeText " +
//                        " FROM `profile` a,`feature` b,`featureversion` c,`featureversionreference` d, " +
//                        " `location` e,`locationpart` f,`locationpartvalue` g,`locparttype` h,`locationcountry` i " +
//                        " WHERE a.`PrimaryKey`=b.`FK_Profile` AND b.`PrimaryKey`=c.`FK_Feature`  " +
//                        " AND c.`ID`=d.`FeatureVersionID` AND d.`FK_Location`=e.`PrimaryKey`  " +
//                        " AND f.`FK_Location`=e.`PrimaryKey`  AND g.`FK_LocationPart`=f.`PrimaryKey`  " +
//                        " AND LocPartTypeID=h.`ID` AND a.`id`=? AND i.`FK_Location`=e.`PrimaryKey` LIMIT 1";
//                records = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
//            } catch (EmptyResultDataAccessException e) {
//                records = " ";
//                System.err.println("Error getAddress: " + e.getMessage());
//            }
//            return records;
//        });
//    }
//
//    @Async("asyncExecutor")
//    public Future<String> getPartyType(Integer id) {
//        return CompletableFuture.supplyAsync(() -> {
//            String PartyType = "";
//            try {
//                String sql = "SELECT " +
//                        " (SELECT IF(b.Text='Unknown',a.Text,b.Text) FROM partytype a,partysubtype b WHERE a.ID=b.PartyTypeID AND b.`ID`=PartySubTypeID) " +
//                        " AS ptype FROM PROFILE a WHERE a.`ID`=?";
//                PartyType = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
//            } catch (EmptyResultDataAccessException e) {
//                PartyType = " ";
//                System.err.println("Error getPartyType: " + e.getMessage());
//            }
//            return PartyType;
//        });
//    }
//
//    @Async("asyncExecutor")
//    public Future<String> getProgram(Integer id) {
//        return CompletableFuture.supplyAsync(() -> {
//            String Program = "";
//            try {
//                String sql = " Select a.textdata from (SELECT GROUP_CONCAT(b.`Comment`) AS textdata,1" +
//                        "  FROM `sanctionctionsentry` a" +
//                        "  JOIN `sanctionsmeasure` b ON a.`PrimaryKey` = b.`FK_SanctionsEntry`" +
//                        "  JOIN `sanctionstype` c ON b.`SanctionsTypeID` = c.`ID`" +
//                        "  WHERE b.`Comment` IS NOT NULL AND a.`ProfileID` = ?" +
//                        "  GROUP BY 2) a";
//                Program = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
//            } catch (EmptyResultDataAccessException e) {
//                Program = " ";
//                System.err.println("Error getProgram: " + e.getMessage());
//            }
//            return Program;
//        });
//    }
//
//    @Async("asyncExecutor")
//    public Future<String> getliststatus(Integer id) {
//        return CompletableFuture.supplyAsync(() -> {
//            String liststatus = "";
//            try {
//                String sql = "SELECT c.`Text` as typeText FROM `profile` a,`sanctionctionsentry` b,`list` c WHERE a.`ID`=b.ProfileID AND b.`ListID`=c.`ID` AND a.`ID`= ? LIMIT 1;";
//                liststatus = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
//            } catch (EmptyResultDataAccessException e) {
//                liststatus = " ";
//                System.err.println("Error getliststatus: " + e.getMessage());
//            }
//            return liststatus;
//        });
//    }
//    public List<Integer> getliststatus() {
//        List<Integer> listStatus = new ArrayList<>();
//            try {
//                String sql = "SELECT ID FROM `profile` GROUP BY ID;";
//                listStatus = jdbcTemplate.queryForList(sql, Integer.class);
////                Future<HashMap<String, String>> mapRetFuture = getValue(id);
//                processValuesForIds(listStatus);
//                return listStatus;
//            } catch (EmptyResultDataAccessException e) {
//                System.err.println("Error getliststatus: " + e.getMessage());
//                return listStatus;
//            }
//    }
//
//        @Async("asyncExecutor")// Process list of IDs asynchronously
//        public CompletableFuture<HashMap<Integer, HashMap<String, String>>> processValuesForIds(List<Integer> ids) {
//            HashMap<Integer, HashMap<String, String>> resultMap = new HashMap<>();
//            List<CompletableFuture<Void>> futures = new ArrayList<>();
//
//            for (Integer id : ids) {
//                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//                    try {
//                        Future<HashMap<String, String>> valueFuture = getValue(id);
//                        HashMap<String, String> values = valueFuture.get();
//                        resultMap.put(id, values);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                });
//                futures.add(future);
//            }
//
//            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
//            return CompletableFuture.completedFuture(resultMap);
//        }

//   @Cacheable(value = "OfacDataDetail", key = "#id")

    public CompletableFuture<HashMap<String, String>> getValue(Integer id) {
        IMap<Integer, HashMap<String, String>> cacheMap1 = getCacheMap();

        HashMap<String, String> mapRet1 = cacheMap1.get(id);  //new HashMap<>();
//        mapRet1 = cacheMap.get(id);// Retrieve the cached data using the key
        //System.out.println("Retrieved from cache: " + mapRet1);

//        if (mapRet1 != null && !mapRet1.isEmpty()) {
        return CompletableFuture.completedFuture(mapRet1);
//    }

//        if (mapRet1 instanceof List) {
//            return CompletableFuture.completedFuture(mapRet1);
////            return (Future<HashMap<String, String>>) mapRet1;
//        }
//        return cacheMap.get(key); // Return an empty list if no data is found

//        HashMap<String, String> mapRet = new HashMap<>();
//        try {
//            // Execute the query and process the result set
//            Map<String, String> result = jdbcTemplate.query(
//                    " SELECT 'Type' AS heading," +
//                            " (SELECT IF(b.Text='Unknown',a.Text,b.Text) AS val" +
//                            " FROM partytype a,partysubtype b WHERE a.ID=b.PartyTypeID AND b.`ID`=PartySubTypeID)" +
//                            " AS val FROM PROFILE a WHERE a.`ID`=" + id  +
//                            " UNION ALL" +
//                            " SELECT e.`Text` AS heading,GROUP_CONCAT(i.`Value`) AS val" +
//                            " FROM versiondetail a,featureversion b,feature c,`profile` d,featuretype e," +
//                            "`versionlocation` f,`location` g,`locationpart` h,`locationpartvalue` i" +
//                            " WHERE a.`FK_FeatureVersion`=b.`PrimaryKey` AND b.`FK_Feature`=c.`PrimaryKey` AND c.`FK_Profile`=d.`PrimaryKey`" +
//                            " AND e.`ID`=c.`FeatureTypeID` AND h.`FK_Location`=g.`PrimaryKey` AND i.`FK_LocationPart`=h.`PrimaryKey`" +
//                            " AND f.`FK_FeatureVersion`=b.`PrimaryKey` AND f.`LocationID`=g.`ID`" +
//                            " AND d.`ID`=" + id +" AND e.id IN (10,11) GROUP BY 1" +
//                            " UNION ALL" +
//                            " SELECT 'Program' AS heading ,a.textdata AS val FROM (SELECT GROUP_CONCAT(b.`Comment`) AS textdata,1" +
//                            " FROM `sanctionctionsentry` a" +
//                            " JOIN `sanctionsmeasure` b ON a.`PrimaryKey` = b.`FK_SanctionsEntry`" +
//                            " JOIN `sanctionstype` c ON b.`SanctionsTypeID` = c.`ID`" +
//                            " WHERE b.`Comment` IS NOT NULL AND a.`ProfileID` = " + id +" GROUP BY 2) a" +
//                            " UNION ALL" +
//                            " SELECT 'Address' AS heading ,GROUP_CONCAT(DISTINCT j.`Text`) AS val" +
//                            " FROM `profile` a,`feature` b,`featureversion` c,`featureversionreference` d," +
//                            " `location` e,`locationpart` f,`locationpartvalue` g,`locparttype` h,`locationcountry` i,`country` j" +
//                            " WHERE a.`PrimaryKey`=b.`FK_Profile` AND b.`PrimaryKey`=c.`FK_Feature`" +
//                            " AND c.`ID`=d.`FeatureVersionID` AND d.`FK_Location`=e.`PrimaryKey`" +
//                            " AND f.`FK_Location`=e.`PrimaryKey`  AND g.`FK_LocationPart`=f.`PrimaryKey`" +
//                            " AND LocPartTypeID=h.`ID` AND a.`id`=" + id +" AND i.`FK_Location`=e.`PrimaryKey` AND i.`CountryID`=j.`ID`" +
//                            " UNION ALL" +
//                            " SELECT 'Identifications' AS heading ,GROUP_CONCAT(DISTINCT (SELECT TEXT FROM country x1 WHERE  a.`IssuedBy-CountryID`=x1.`ID`)) AS val" +
//                            " FROM IDRegDocument a" +
//                            " LEFT OUTER JOIN  `documentdate` b ON a.`PrimaryKey`=b.`FK_IDRegDocument`" +
//                            " LEFT JOIN dateperiod c ON  c.`FK_DocumentDate`=b.`PrimaryKey`" +
//                            " LEFT JOIN `start` d ON d.`FK_DatePeriod`=c.`PrimaryKey`" +
//                            " LEFT JOIN `from` e ON e.`FK_Start`=d.`PrimaryKey` ,identity f" +
//                            " WHERE f.`ID`=a.`IdentityID` AND f.`FixedRef`=" + id ,
//                    new ResultSetExtractor<Map<String, String>>() {
//                        @Override
//                        public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
//                            while (rs.next()) {
//                                mapRet.put(rs.getString("heading"), rs.getString("val"));
//                            }
//                            IMap<Integer, HashMap<String, String>> cacheMap = getCacheMap();
//                            cacheMap.putIfAbsent(id,mapRet);
//
//                            return mapRet;
//                        }
//                    }
//
//            );
//
//        } catch (DataAccessException dae) {
//            System.err.println("DataAccessException occurred: " + dae.getMessage());
//            dae.printStackTrace();
//
//        } catch (Exception e) {
//            System.err.println("Exception occurred: " + e.getMessage());
//            e.printStackTrace();
//        }
        // Wrap the result in a CompletableFuture
//        return CompletableFuture.completedFuture(mapRet);
    }

    public void setCacheData() {
            try {

                String sql = " SELECT a.`ID` as id FROM PROFILE a GROUP BY a.`ID`";
                List<Integer> ids = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"));
                if (ids.isEmpty()) {
                    System.out.println("No IDs found.");
                }
                for (Integer id : ids) {
//                    log.info("Start Fn"+id+"    : "+now());
//                    getValue(id);
                    setCacheValue(id);
//                    log.info("End Fn"+id+"    : "+now());
                }
            } catch (EmptyResultDataAccessException e) {
                System.err.println("Error getProgram: " + e.getMessage());
            }
    }
///////////////////////////////////////
public CompletableFuture<HashMap<String, String>> setCacheValue(Integer id) {
    IMap<Integer, HashMap<String, String>> cacheMap = getCacheMap();
    cacheMap.remove(id);

    HashMap<String, String> mapRet = new HashMap<>();
    try {
        // Execute the query and process the result set
        Map<String, String> result = jdbcTemplate.query(
                "SELECT ids,heading,val FROM  ("+
                        " SELECT ids,heading,val FROM san_sort_type"+
                        " UNION"+
                        " SELECT ids,heading,val FROM san_sort_country"+
                        " UNION"+
                        " SELECT ids,heading,val FROM san_sort_program"+
                        " UNION"+
                        " SELECT ids,heading,val FROM san_sort_address"+
                        " UNION "+
                        " SELECT ids,heading,val FROM san_sort_identification) a where ids="+id ,
                new ResultSetExtractor<Map<String, String>>() {
                    @Override
                    public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        while (rs.next()) {
                            mapRet.put(rs.getString("heading"), rs.getString("val"));
                        }
                        IMap<Integer, HashMap<String, String>> cacheMap = getCacheMap();
                        cacheMap.putIfAbsent(id,mapRet);

                        return mapRet;
                    }
                }
        );
    } catch (DataAccessException dae) {
        System.err.println("DataAccessException occurred: " + dae.getMessage());
        dae.printStackTrace();

    } catch (Exception e) {
        System.err.println("Exception occurred: " + e.getMessage());
        e.printStackTrace();
    }
    // Wrap the result in a CompletableFuture
    return CompletableFuture.completedFuture(mapRet);
}
///////////////////////////////////////
//    @Async("asyncExecutor")
//    public Future<String> getPassport(Integer id) {
//        return CompletableFuture.supplyAsync(() -> {
//            String passport = "";
//            try {
//                String sql = " SELECT " +
//                        " (SELECT TEXT FROM idregdoctype x2 WHERE a.`IDRegDocTypeID`=x2.id ) AS TYPE," +
//                        " (SELECT TEXT FROM country x1 WHERE  a.`IssuedBy-CountryID`=x1.`ID`) AS country," +
//                        " GROUP_CONCAT(a.`IDRegistrationNo`) AS IDs" +
//                        " FROM IDRegDocument a " +
//                        " LEFT OUTER JOIN  `documentdate` b ON a.`PrimaryKey`=b.`FK_IDRegDocument` " +
//                        " LEFT JOIN dateperiod c ON  c.`FK_DocumentDate`=b.`PrimaryKey` " +
//                        " LEFT JOIN `start` d ON d.`FK_DatePeriod`=c.`PrimaryKey`" +
//                        " LEFT JOIN `from` e ON e.`FK_Start`=d.`PrimaryKey` ,identity f" +
//                        " WHERE f.`ID`=a.`IdentityID` AND f.`FixedRef`=? AND a.`IDRegDocTypeID`=1571 GROUP BY 1";
//                passport = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
//            } catch (EmptyResultDataAccessException e) {
//                passport = " ";
//                System.err.println("Error getProgram: " + e.getMessage());
//            }
//            return passport;
//        });
//    }
}

