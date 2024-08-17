package com.ponsun.san.algorithm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@Slf4j
@RequiredArgsConstructor
public class SanRecordDetails {
    private final JdbcTemplate jdbcTemplate;
    /////////////////
    public String getProgram(Integer id) {
        String records = "";
        try {
            //String sql = "SELECT Program(?) as Program";
            String sql =" Select a.textdata from (SELECT GROUP_CONCAT(b.`Comment`) AS textdata,1" +
                    "  FROM `sanctionctionsentry` a" +
                    "  JOIN `sanctionsmeasure` b ON a.`PrimaryKey` = b.`FK_SanctionsEntry`" +
                    "  JOIN `sanctionstype` c ON b.`SanctionsTypeID` = c.`ID`" +
                    "  WHERE b.`Comment` IS NOT NULL AND a.`ProfileID` = ?" +
                    "  GROUP BY 2) a" ;
            records = jdbcTemplate.queryForObject(sql,new Object[]{id}, String.class);
            return records;
        } catch (DataAccessException e) {
            records = " ";
            System.err.println("Error getProgram: " + e.getMessage());
        }
        return records;
    }
    /////////////////
//    @Async("asyncExecutor")
//    public String getAddress(Integer id) {
//        String records = "";
//        try {
//            //String sql = "SELECT Address(?) as Address";
//            String sql = "SELECT VALUE as typeText " +
//                    " FROM `profile` a,`feature` b,`featureversion` c,`featureversionreference` d, " +
//                    " `location` e,`locationpart` f,`locationpartvalue` g,`locparttype` h,`locationcountry` i " +
//                    " WHERE a.`PrimaryKey`=b.`FK_Profile` AND b.`PrimaryKey`=c.`FK_Feature`  " +
//                    " AND c.`ID`=d.`FeatureVersionID` AND d.`FK_Location`=e.`PrimaryKey`  " +
//                    " AND f.`FK_Location`=e.`PrimaryKey`  AND g.`FK_LocationPart`=f.`PrimaryKey`  " +
//                    " AND LocPartTypeID=h.`ID` AND a.`id`=? AND i.`FK_Location`=e.`PrimaryKey` LIMIT 1;";
//            records = jdbcTemplate.queryForObject(sql,new Object[]{id}, String.class));
//
//        } catch (EmptyResultDataAccessException e) {
//            records = " ";
//            System.err.println("Error getAddress: " + e.getMessage());
//            //e.printStackTrace();
//        }
//        return records;
//    }
    @Async("asyncExecutor")
    public Future<String> getAddress(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            String records = "";
            try {
                String sql = "SELECT VALUE as typeText " +
                        " FROM `profile` a,`feature` b,`featureversion` c,`featureversionreference` d, " +
                        " `location` e,`locationpart` f,`locationpartvalue` g,`locparttype` h,`locationcountry` i " +
                        " WHERE a.`PrimaryKey`=b.`FK_Profile` AND b.`PrimaryKey`=c.`FK_Feature`  " +
                        " AND c.`ID`=d.`FeatureVersionID` AND d.`FK_Location`=e.`PrimaryKey`  " +
                        " AND f.`FK_Location`=e.`PrimaryKey`  AND g.`FK_LocationPart`=f.`PrimaryKey`  " +
                        " AND LocPartTypeID=h.`ID` AND a.`id`=? AND i.`FK_Location`=e.`PrimaryKey` LIMIT 1;";
                records = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
            } catch (EmptyResultDataAccessException e) {
                records = " ";
                System.err.println("Error getAddress: " + e.getMessage());
            }
            return records;
        });
    }
    ////////////////

    public String getTempAddress(Integer id) {

            String records = "";
            try {
                String sql = "SELECT VALUE as typeText " +
                        " FROM `profile` a,`feature` b,`featureversion` c,`featureversionreference` d, " +
                        " `location` e,`locationpart` f,`locationpartvalue` g,`locparttype` h,`locationcountry` i " +
                        " WHERE a.`PrimaryKey`=b.`FK_Profile` AND b.`PrimaryKey`=c.`FK_Feature`  " +
                        " AND c.`ID`=d.`FeatureVersionID` AND d.`FK_Location`=e.`PrimaryKey`  " +
                        " AND f.`FK_Location`=e.`PrimaryKey`  AND g.`FK_LocationPart`=f.`PrimaryKey`  " +
                        " AND LocPartTypeID=h.`ID` AND a.`id`=? AND i.`FK_Location`=e.`PrimaryKey` LIMIT 1;";
                return  records = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
            } catch (EmptyResultDataAccessException e) {
                records = " ";
                System.err.println("Error getAddress: " + e.getMessage());
            }
            return records;

    }
    /////////////////
    public String getliststatus(Integer id) {
        try {
            //String sql = "SELECT liststatus(?) as liststatus";
            String sql = "SELECT c.`Text` as typeText FROM `profile` a,`sanctionctionsentry` b,`list` c WHERE a.`ID`=b.ProfileID AND b.`ListID`=c.`ID` AND a.`ID`= ? LIMIT 1;";
            String records = jdbcTemplate.queryForObject(sql,new Object[]{id}, String.class);
            return records;
        } catch (DataAccessException e) {
            System.err.println("Error getliststatus: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    ////////////////
    /////////////////


    public String getAddrAddress(Integer Entity_logical_id_Addr) {
        try {
            String sql = "SELECT Addr_street FROM test_eu_sanction WHERE Entity_logical_id_Addr= ? AND Addr_street!='' LIMIT 1";
            String records = jdbcTemplate.queryForObject(sql,new Object[]{Entity_logical_id_Addr}, String.class);
            return records;
        } catch (DataAccessException e) {
            System.err.println("Error getAddrAddress: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public String getSubjectTypeProgram(Integer Entity_logical_id) {
        try {
            String sql = " SELECT CONCAT(Subject_type,'<>',Naal_programme) FROM test_eu_sanction WHERE Entity_logical_id = ? LIMIT 1";
            String records = jdbcTemplate.queryForObject(sql,new Object[]{Entity_logical_id}, String.class);
            return records;
        } catch (DataAccessException e) {
            System.err.println("Error getSubjectTypeProgram: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public String getAddressSubjectTypeProgram(Integer Group_ID) {
        try {
            String sql = " SELECT CONCAT (Address_1, '<>' , Group_Type, '<>' , Regime, '<>' , Regime) FROM test_uk_sanction WHERE Group_ID= ? AND Alias_Type= 'Primary name' ORDER BY 1 LIMIT 1";
            String records = jdbcTemplate.queryForObject(sql, new Object[]{Group_ID}, String.class);
            return records;
        } catch (DataAccessException e) {
            System.err.println("Error getAddressSubjectTypeProgram: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public String getAddressListProgram(Integer DATAID) {
        try {
            String sql = " SELECT CONCAT( UN_LIST_TYPE, '<>', UN_LIST_TYPE, '<>', CONCAT(INDIVIDUAL_PLACE_OF_BIRTH_0_CITY, ',', INDIVIDUAL_PLACE_OF_BIRTH_0_STATE_PROVINCE)) AS address FROM  test_un_sanction  WHERE DATAID = ?";
            String records = jdbcTemplate.queryForObject(sql, new Object[]{DATAID}, String.class);
            return records;
        } catch (DataAccessException e) {
            System.err.println("Error getAddressListProgram: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
