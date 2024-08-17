package com.ponsun.san.ofac.Count.service;
import com.ponsun.san.EhcachePOC.Data.OFACData;
import com.ponsun.san.EhcachePOC.Service.OFACSearchService;
import com.ponsun.san.algorithm.ScoringCalculator;
import com.ponsun.san.dto.RecordDTO;
import com.ponsun.san.dto.SearchDTO;
import com.ponsun.san.ofac.Count.rowmapper.CountRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.Count;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountReadServiceImpl implements CountReadService{
    private final JdbcTemplate jdbcTemplate;
    private final ScoringCalculator scoringCalculator;
    private final OFACSearchService ofacsearchService;
    @Override
    @Transactional
    public List<Count> fetchAllCount(Integer id) {
        return this.fetchAllCount(id);
    }
    @Override
    @Transactional
    public List<RecordDTO> getRecordsCount(SearchDTO searchDTO) {
        try {
            System.out.println("Name : " + searchDTO.getName());
            final CountRowMapper rowMapper = new CountRowMapper();

            List<OFACData> combinedDTOList = new LinkedList<>();

            int limitSize = 0;

            Integer countryId = searchDTO.getCountryId();
            Integer PartySubTypeID = searchDTO.getPartySubTypeID();
            Integer ListID = searchDTO.getListID();
            String QryCluster = " ";
            if (PartySubTypeID != 0)
                QryCluster += " AND PartySubTypeID=" + PartySubTypeID;
            if (ListID != 0)
                QryCluster += " AND b1.`ListID`=" + ListID;

//            String Qry = " SELECT id, NAME, fileType, REGEXP_REPLACE(NAME, '[^a-zA-Z0-9]', ' '), _list" +
//                    " FROM (SELECT id, " +
//                    " TRIM(TRAILING ',' FROM CONCAT(SUBSTRING_INDEX(NAME, ',', 1), ',', TRIM(REPLACE(REPLACE(NAME, " +
//                    " SUBSTRING_INDEX(NAME, ',', 1), ''), ',', ' ')))) AS NAME, " +
//                    " 1 AS fileType, 'US OFAC' AS _list " +
//                    " FROM (SELECT id, " +
//                    " GROUP_CONCAT(NAME) AS NAME " +
//                    " FROM " +
//                    " ( " +
//                    " SELECT a.FixedRef AS id, " +
//                    " g.Text AS NAME, FK_DocumentedName " +
//                    " FROM " +
//                    " distinctparty a, PROFILE b LEFT JOIN sanctionctionsentry b1 ON b.ID = b1.ProfileID, identity c, alias d, documentedname e, documentednamepart f, namepartvalue g, namepartgroup h, nameparttype i " +
//                    " WHERE a.PrimaryKey = b.FK_DistinctParty AND b.PrimaryKey = c.FK_Profile " +
//                    " AND c.PrimaryKey = d.FK_Identity AND d.PrimaryKey = e.FK_Alias AND g.ScriptID = 215 " +
//                    " AND e.PrimaryKey = f.FK_DocumentedName AND f.PrimaryKey = g.FK_DocumentedNamePart " +
//                    " AND g.NamePartGroupID = h.ID AND h.NamePartTypeID = i.ID " +
//                    " ORDER BY FK_DocumentedName, i.ID) a GROUP BY FK_DocumentedName ) b " +
//                    " UNION " +
//                    " SELECT * FROM (SELECT Entity_logical_id AS id, TRIM(TRAILING ',' FROM Naal_wholename) AS NAME, 2 AS fileType, 'EU LIST' AS _list " +
//                    " FROM test_eu_sanction WHERE Naal_wholename IS NOT NULL AND Naal_wholename != '' AND Naal_language = 'EN' GROUP BY Naal_wholename) a " +
//                    " UNION " +
//                    " SELECT * FROM (SELECT Group_ID AS id, " +
//                    " TRIM(TRAILING ',' FROM CONCAT(name_1, IF(LENGTH(name_1) = 0, '', ' '), " +
//                    " name_2, IF(LENGTH(name_2) = 0, '', ' '), " +
//                    " name_3, IF(LENGTH(name_3) = 0, '', ' '), " +
//                    " name_4, IF(LENGTH(name_4) = 0, '', ' '), " +
//                    " name_5, IF(LENGTH(name_5) = 0, '', ' '), " +
//                    " name_6)) AS NAME, " +
//                    " 3 AS fileType, 'UK HMT' AS _list " +
//                    " FROM `test_uk_sanction` GROUP BY 2) c WHERE c.name != '' " +
//                    " UNION " +
//                    " SELECT * FROM (SELECT DATAID AS id, TRIM(TRAILING ',' FROM CONCAT(FIRST_NAME, ' ', SECOND_NAME, ' ', THIRD_NAME)) AS NAME, 4 AS fileType, 'UN LIST' AS _list FROM `test_un_sanction` " +
//                    " UNION ALL " +
//                    " SELECT DATAID AS id, TRIM(TRAILING ',' FROM INDIVIDUAL_ALIAS_0_ALIAS_NAME) AS NAME, 4 AS fileType, 'UN LIST' AS _list FROM `test_un_sanction` WHERE INDIVIDUAL_ALIAS_0_ALIAS_NAME != '' " +
//                    " UNION ALL " +
//                    " SELECT DATAID AS id, TRIM(TRAILING ',' FROM INDIVIDUAL_ALIAS_1_ALIAS_NAME) AS NAME, 4 AS fileType, 'UN LIST' AS _list FROM `test_un_sanction` WHERE INDIVIDUAL_ALIAS_1_ALIAS_NAME != '' " +
//                    " UNION ALL " +
//                    " SELECT DATAID AS id, TRIM(TRAILING ',' FROM INDIVIDUAL_ALIAS_2_ALIAS_NAME) AS NAME, 4 AS fileType, 'UN LIST' AS _list FROM `test_un_sanction` WHERE INDIVIDUAL_ALIAS_2_ALIAS_NAME != '') D) a";
//
//            final List<CountData> lookUpSearchData = jdbcTemplate.query(Qry, new Object[]{}, rowMapper);
            List<OFACData> ofacdataList = this.ofacsearchService.fetchAllOFACData();
            List<RecordDTO> recordDTOList = this.scoringCalculator.calculateScore(searchDTO, ofacdataList);
            combinedDTOList.addAll(ofacdataList);

            return recordDTOList;
        } catch (DataAccessException e) {
            System.err.println("Error getTotalRecordsCount: " + e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Integer getTotalRecordsCount() {
        try {
            String sql = "SELECT COUNT(*) FROM `namepartvalue`";
            Integer recordsCount = jdbcTemplate.queryForObject(sql, Integer.class);
            return recordsCount;

        } catch (DataAccessException e) {
            System.err.println("Error getTotalRecordsCount: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    /////////////////Service

}



//    public List<RecordDTO> getRecordsCount(SearchDTO searchDTO) {
//        try {
//            System.out.println("Name : "+searchDTO.getName());
//            final CountRowMapper rowMapper = new CountRowMapper();
////            int count=getTotalRecordsCount();
////            int loopCount = count/10000;
//            List<CountData> combinedDTOList = new ArrayList<>();
//    // ExecutorService executor = Executors.newFixedThreadPool(5);
//    // for (int i=0;i<loopCount;i++)
//
//    //  final int currentIteration = i;
//    int limitSize= 0;
//    // executor.submit(() -> {
//    // Your code for each iteration goes here
//    // For example
//    // System.out.println("Processing iteration: " + currentIteration);
//    Integer countryId= searchDTO.getCountryId();
//    Integer PartySubTypeID = searchDTO.getPartySubTypeID();
//    Integer ListID  = searchDTO.getListID();
//    String QryCluster   = " ";
//    if(PartySubTypeID!=0)
//        QryCluster  += " AND PartySubTypeID="+ PartySubTypeID;
//    if(ListID!=0)
//        QryCluster  += " AND b1.`ListID`=" +ListID;
//
//            //String Qry = "SELECT PrimaryKey as id,TEXT as name FROM `namepartvalue` LIMIT ?,100000";
//            //String Qry  = "SELECT a.`FixedRef` AS id,e.`Text` AS NAME FROM identity a,alias b,documentedname c,documentednamepart d,namepartvalue e WHERE b.`FK_Identity`=a.`PrimaryKey` AND c.`FK_Alias`=b.`PrimaryKey` AND d.`FK_DocumentedName`=c.`PrimaryKey` AND e.`FK_DocumentedNamePart`=d.`PrimaryKey` AND e.`ScriptID`=215";
////            String Qry  = "SELECT id, " +
////                    "CONCAT(SUBSTRING_INDEX(GROUP_CONCAT(NAME  SEPARATOR ','), ',', 1),',',TRIM(REPLACE(REPLACE(GROUP_CONCAT(NAME  SEPARATOR ','),SUBSTRING_INDEX(GROUP_CONCAT(NAME  SEPARATOR ','), ',', 1),''),',',' '))),TRIM(REPLACE(REPLACE(GROUP_CONCAT(NAME  SEPARATOR ','),SUBSTRING_INDEX(GROUP_CONCAT(NAME  SEPARATOR ','), ',', 1),''),',',' ')) AS NAME, " +
////                    "SUBSTRING_INDEX(GROUP_CONCAT(NAME  SEPARATOR ','), ',', 1) AS aa " +
////                    "FROM  " +
////                    "( " +
////                    "SELECT a.FixedRef AS id, " +
////                    "g.Text AS NAME,FK_DocumentedName " +
////                    "FROM " +
////                    "distinctparty a,PROFILE b LEFT JOIN sanctionctionsentry b1 ON b.`ID`=b1.ProfileID,identity c,alias d,documentedname e,documentednamepart f,namepartvalue g,`namepartgroup` h,`nameparttype` i " +
////                    "WHERE a.PrimaryKey=b.FK_DistinctParty AND b.PrimaryKey=c.FK_Profile " +
////                    "AND c.PrimaryKey=d.FK_Identity AND d.PrimaryKey=e.FK_Alias AND g.ScriptID=215 " +
////                    "AND e.PrimaryKey=f.FK_DocumentedName AND f.PrimaryKey=g.FK_DocumentedNamePart  " +
////                    "AND g.`NamePartGroupID`=h.`ID` AND h.`NamePartTypeID`=i.`ID`  " +
////                    "ORDER BY FK_DocumentedName,i.`ID`) a GROUP BY FK_DocumentedName";
//            String Qry = " SELECT id,NAME,fileType,REGEXP_REPLACE(NAME, '[^a-zA-Z0-9]', ' '),_list" +
//                    " FROM (SELECT id, " +
//                    " CONCAT(SUBSTRING_INDEX(NAME, ',', 1),',',TRIM(REPLACE(REPLACE(NAME,  " +
//                    " SUBSTRING_INDEX(NAME, ',', 1),''),',',' '))) AS NAME,  " +
//                    " 1 AS fileType,'US OFAC' AS _list " +
//                    " FROM (SELECT id,  " +
//                    " GROUP_CONCAT(NAME) AS NAME  " +
//                    " FROM   " +
//                    " (  " +
//                    " SELECT a.FixedRef AS id,  " +
//                    " g.Text AS NAME,FK_DocumentedName  " +
//                    " FROM  " +
//                    " distinctparty a,PROFILE b LEFT JOIN sanctionctionsentry b1 ON b.ID=b1.ProfileID,identity c,alias d,documentedname e,documentednamepart f,namepartvalue g,namepartgroup h,nameparttype i  " +
//                    " WHERE a.PrimaryKey=b.FK_DistinctParty AND b.PrimaryKey=c.FK_Profile  " +
//                    " AND c.PrimaryKey=d.FK_Identity AND d.PrimaryKey=e.FK_Alias AND g.ScriptID=215  " +
//                    " AND e.PrimaryKey=f.FK_DocumentedName AND f.PrimaryKey=g.FK_DocumentedNamePart   " +
//                    " AND g.NamePartGroupID=h.ID AND h.NamePartTypeID=i.ID   " +
//                    " ORDER BY FK_DocumentedName,i.ID) a GROUP BY FK_DocumentedName ) b   " +
//                    " UNION " +
//                    " SELECT * FROM (SELECT Entity_logical_id AS id,Naal_wholename  AS NAME,2 AS fileType,'EU LIST' AS _list " +
//                    " FROM test_eu_sanction WHERE Naal_wholename IS NOT NULL AND Naal_wholename != '' AND Naal_language='EN' GROUP BY Naal_wholename) a " +
//                    " UNION " +
//                    " SELECT * FROM (SELECT Group_ID AS id," +
//                    " CONCAT(name_1,IF(LENGTH(name_1)=0,'',' ')," +
//                    " name_2,IF(LENGTH(name_2)=0,'',' ')," +
//                    " name_3,IF(LENGTH(name_3)=0,'',' ')," +
//                    " name_4,IF(LENGTH(name_4)=0,'',' ')," +
//                    " name_5,IF(LENGTH(name_5)=0,'',' ')," +
//                    " name_6" +
//                    " ) AS NAME," +
//                    " 3 AS fileType,'UK HMT' AS _list " +
//                    " FROM `test_uk_sanction` GROUP BY 2) c  WHERE c.name !=''" +
//                    " UNION" +
//                    " SELECT * FROM (SELECT DATAID AS id,CONCAT(FIRST_NAME,' ',SECOND_NAME,' ',THIRD_NAME) AS NAME,4 AS fileType,'UN LIST' AS _list FROM `test_un_sanction` " +
//                    " UNION ALL " +
//                    " SELECT DATAID AS id,INDIVIDUAL_ALIAS_0_ALIAS_NAME AS NAME,4 AS fileType,'UN LIST' AS _list FROM `test_un_sanction`  WHERE INDIVIDUAL_ALIAS_0_ALIAS_NAME !=''" +
//                    " UNION ALL " +
//                    " SELECT DATAID AS id,INDIVIDUAL_ALIAS_1_ALIAS_NAME AS NAME,4 AS fileType,'UN LIST' AS _list FROM `test_un_sanction` WHERE INDIVIDUAL_ALIAS_1_ALIAS_NAME!=''" +
//                    " UNION ALL " +
//                    " SELECT DATAID AS id,INDIVIDUAL_ALIAS_2_ALIAS_NAME AS NAME,4 AS fileType,'UN LIST' AS _list FROM `test_un_sanction` WHERE INDIVIDUAL_ALIAS_2_ALIAS_NAME !=''" +
//                    " ) D" +
//                    " ) a" ;
//
//                final List<CountData> lookUpSearchData = jdbcTemplate.query(Qry,new Object[]{}, rowMapper);
//                List<RecordDTO> recordDTOList= this.scoringCalculator.calculateScore(searchDTO, lookUpSearchData);
//                //System.out.println(recordDTOList);
//                combinedDTOList.addAll(lookUpSearchData);
//            //});
//
//            //executor.shutdown();
//
////          System.out.println(combinedDTOList);
//
//            return recordDTOList;
//        } catch (DataAccessException e) {
//            System.err.println("Error getTotalRecordsCount: " + e.getMessage());
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        return null;
//    }

