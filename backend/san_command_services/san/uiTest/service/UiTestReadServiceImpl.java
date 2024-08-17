package com.ponsun.san.uiTest.service;
import com.ponsun.san.ofac.Count.data.CountData;
import com.ponsun.san.ofac.Count.rowmapper.CountRowMapper;
import com.ponsun.san.uiTest.UiAlgorithemVerification;
import com.ponsun.san.uiTest.UiScoringCalculator;
import com.ponsun.san.uiTest.dto.UiReciveRecord;
import com.ponsun.san.uiTest.dto.UiRecordDTO;
import com.ponsun.san.uiTest.dto.UiSearchDTO;
import com.ponsun.san.uiTest.dto.UiSearchDtoVerify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UiTestReadServiceImpl implements UiTestReadService {
    private final JdbcTemplate jdbcTemplate;
    private final UiScoringCalculator scoringCalculator;
    private final UiAlgorithemVerification algorithemVerification;

    @Override
    @Transactional
    public List<UiRecordDTO> getuiTestRecords(UiSearchDTO searchDTO) {
        try {
            //System.out.println("Name : "+searchDTO.getName());
            final CountRowMapper rowMapper = new CountRowMapper();

            List<CountData> combinedDTOList = new ArrayList<>();
            int limitSize= 0;

            String Qry = "SELECT id,  " +
                    "CONCAT(SUBSTRING_INDEX(NAME, ',', 1),',',TRIM(REPLACE(REPLACE(NAME,  " +
                    "SUBSTRING_INDEX(NAME, ',', 1),''),',',' '))) AS name,  " +
                    " '' as orgName "+
                    "FROM (SELECT id,  " +
                    "GROUP_CONCAT(NAME) AS NAME  " +
                    "FROM   " +
                    "(  " +
                    "SELECT a.FixedRef AS id,  " +
                    "g.Text AS NAME,FK_DocumentedName  " +
                    "FROM  " +
                    "distinctparty a,PROFILE b LEFT JOIN sanctionctionsentry b1 ON b.`ID`=b1.ProfileID,identity c,alias d,documentedname e,documentednamepart f,namepartvalue g,`namepartgroup` h,`nameparttype` i  " +
                    "WHERE a.PrimaryKey=b.FK_DistinctParty AND b.PrimaryKey=c.FK_Profile  " +
                    "AND c.PrimaryKey=d.FK_Identity AND d.PrimaryKey=e.FK_Alias AND g.ScriptID=215  " +
                    "AND e.PrimaryKey=f.FK_DocumentedName AND f.PrimaryKey=g.FK_DocumentedNamePart   " +
                    "AND g.`NamePartGroupID`=h.`ID` AND h.`NamePartTypeID`=i.`ID`   " +//+QryCluster+
                    " ORDER BY FK_DocumentedName,i.`ID`) a GROUP BY FK_DocumentedName ) b   " +
                    "  ";
                final List<CountData> lookUpSearchData = jdbcTemplate.query(Qry,new Object[]{}, rowMapper);

                List<UiRecordDTO> recordDTOList= this.scoringCalculator.uicalculateScore(searchDTO, lookUpSearchData);
                //System.out.println(recordDTOList);
                combinedDTOList.addAll(lookUpSearchData);
            //});
            //executor.shutdown();
            //System.out.println(combinedDTOList);

            return recordDTOList;
        } catch (DataAccessException e) {
            System.err.println("Error getTotalRecordsCount: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UiReciveRecord> getUiRecords(UiSearchDtoVerify uiSearchDtoVerify) {
        List<UiReciveRecord> recordDTOList= this.algorithemVerification.uicalculateScore(uiSearchDtoVerify);
        return recordDTOList;
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
    @Override
    public String calculateJaroWinklerSimilarity(String str1, String str2) {
        String sql = "SELECT jaro_winkler_similarity(?, ?) AS similarity";
        String similarity = jdbcTemplate.queryForObject(sql, new Object[]{str1, str2}, String.class);
        return similarity;
    }

}
