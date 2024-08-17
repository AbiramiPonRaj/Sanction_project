package com.ponsun.san.uiTest;

import com.ponsun.san.algorithm.cdo.CriminalRuleCDO;

import com.ponsun.san.ofac.Count.data.CountData;
import com.ponsun.san.uiTest.dto.UiRecordDTO;
import com.ponsun.san.uiTest.dto.UiSearchDTO;
import info.debatty.java.stringsimilarity.*;
import info.debatty.java.stringsimilarity.experimental.Sift4;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.Nysiis;
import org.apache.commons.codec.language.RefinedSoundex;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.lang3.StringUtils;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Float.parseFloat;
import static java.lang.Math.max;
import static me.xdrop.fuzzywuzzy.FuzzySearch.*;
//import static org.apache.commons.text.similarity;
import static org.apache.commons.lang3.StringUtils.length;
import static org.apache.commons.math3.util.Precision.round;
import java.util.Collection;
@Service
@Slf4j
@RequiredArgsConstructor
public class UiScoringCalculator {

    private final KieContainer kieContainer;
    //private final List<StateData> stateDataList;
    private final JdbcTemplate jdbcTemplate;
    Damerau Dam     = new Damerau();//Damerau-Levenshtein
    JaroWinkler jw          = new JaroWinkler();//JW Match

    DoubleMetaphone dm      = new DoubleMetaphone();
    Cosine cosine           = new Cosine(2);// Let's work with sequences of 2 characters...
    RefinedSoundex rsound   = new RefinedSoundex();//Refined Soundex
    Soundex Sound           = new Soundex();
    Nysiis Nysi             = new Nysiis();

    Jaccard jac             = new Jaccard();
    NormalizedLevenshtein Nlev  = new NormalizedLevenshtein();
    SorensenDice sDice          = new SorensenDice();

    QGram Qgr                   = new QGram();
    Levenshtein lev             = new Levenshtein();

    MetricLCS Matric        = new MetricLCS();
    RatcliffObershelp rac   = new RatcliffObershelp();
    NGram Ngr               = new NGram();
    OptimalStringAlignment OpStr    =new OptimalStringAlignment();
    LongestCommonSubsequence lngCom    =new LongestCommonSubsequence();
    Sift4 Sif    =new Sift4();
    @Transactional
    public List<UiRecordDTO> uicalculateScore(UiSearchDTO searchDTO, List<CountData> countDataList)
    {
         List<UiRecordDTO> uirecordDTOS = new ArrayList<>();
        //log.debug("StateDataList: {}", stateDataList);

        String queryCustomer = searchDTO.getName().toLowerCase();
        Double matching_score = searchDTO.getMatching_score();

        OptimalStringAlignment OpStr    = new OptimalStringAlignment();
        LongestCommonSubsequence lngCom = new LongestCommonSubsequence();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //FuzzySearch fuzzySearch= new FuzzySearch();
        //for(CountData countData: countDataList) {
            //executor.submit(() -> {
            //String str1 = "IBRAHIM Dawood";//countData.getName().toLowerCase();//name

        String str1 = "Dawood IBRAHIM".toLowerCase();
        //String str1 = "omar mohammed rahman".toLowerCase();
//        String str2 = "";
            //str1= str1.replaceAll(","," ");

//        for (int i=0;i<str1.length();i++)
//        {
////comparing alphabets with their corresponding ASCII value
//            if (str1.charAt(i)>64 && str1.charAt(i)<=122) //returns true if both conditions returns true
//            {
//                str2=str2+str1.charAt(i);
//            }
//        }
        String str2 = str1;//Normalizer.normalize(str1, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");

            // Pre-compute the profile of strings
            Map<String, Integer> profile1 = cosine.getProfile(queryCustomer);
            Map<String, Integer> profile2 = cosine.getProfile(str2);

            dm.setMaxCodeLen(1000);
            String dmvalue1     = dm.doubleMetaphone(queryCustomer);
            String dmvalue2     = dm.doubleMetaphone(str2);

            double dmcosineValue= cosine.similarity(dmvalue1, dmvalue2) * 100;
            double dmjwValue    = jw.similarity(queryCustomer,str2)*100;

            int ratio           = ratio(queryCustomer,str2);
            int sortRatio       = tokenSortRatio(queryCustomer,str2);
            int weightRatio     = weightedRatio(queryCustomer,str2);

            double damroSimilarity = (1-(Dam.distance(queryCustomer,str2)/max(length(queryCustomer),length(str2))))*100;

            int setRatio        = tokenSetRatio(queryCustomer,str2);

            String ratio_sort_weight = "damroSimilarity : "+damroSimilarity+" ratio: "+ratio + "  setRatio : "+setRatio+" - sortRatio: " +sortRatio+" - weightRatio: "+weightRatio;

            String tokenSort    = "0";//tokenSortPartialRatio(queryCustomer,str2)+"";
            double cosineValue  = cosine.similarity(profile1, profile2)*100;            // Prints 0.516185
            double ngrm_Value   = Ngr.distance(queryCustomer, str2) * 100;

            double value2       = jw.similarity(queryCustomer,str2)* 100;
            String soundxVal1   = Sound.soundex(queryCustomer);
            String soundxVal2   = Sound.soundex(str2);
            double SoundcosineValue = cosine.similarity(soundxVal1, soundxVal2)*100;
            //          || ngrm_Value >=matching_score
            System.out.println(value2);
            if(dmcosineValue >= matching_score || parseFloat(tokenSort) >= matching_score
                    || dmjwValue >= matching_score || sortRatio >= matching_score
                    || setRatio >= matching_score || cosineValue >= matching_score
                    || value2 >= matching_score || SoundcosineValue >= matching_score) {

                UiRecordDTO uiRecordDTO = new UiRecordDTO();
                uiRecordDTO.setName(str2);
                uiRecordDTO.setJarow(value2 + "");
                uiRecordDTO.setSet(setRatio + "");
                uiRecordDTO.setSort(ratio_sort_weight + "");
                uiRecordDTO.setFuzzy_Wuzzytoken_sort_ratio(tokenSort);
                uiRecordDTO.setCosine_Similarity(cosineValue + "");
                uiRecordDTO.setDouble_Metaphone(dmcosineValue + "");
                uiRecordDTO.setSoundex_val(SoundcosineValue + "");
                uiRecordDTO.setDouble_Metaphone_jw(dmjwValue + "");
                uiRecordDTO.setN_Gram(ngrm_Value + "");
                uirecordDTOS.add(uiRecordDTO);
            }
        //}
        //executor.shutdown();
        //Collections.sort(recordDTOS, Comparator.comparingDouble(RecordDTO::getScore).reversed());
        return uirecordDTOS;
    }
    private boolean isCriminalRulePassed(final String value1, final String value2,final double matching_score) {
        if(StringUtils.isBlank(value1)) {
            //throw error;
        }
        if(StringUtils.isBlank(value2)) {
            //throw error;
        }
        double value1d = Double.parseDouble(value1);
        double value2d = Double.parseDouble(value2) * 100;
        return isCriminalRulePassed(value1d, value2d,matching_score);
    }

    private boolean isCriminalRulePassed(final double value1,final double value2,final double matching_score) {
        final KieSession ruleSession = kieContainer.newKieSession();
        try {
            final CriminalRuleCDO criminalRuleCDO = new CriminalRuleCDO();
            criminalRuleCDO.setScore1(value1);
            criminalRuleCDO.setScore2(value2);
            criminalRuleCDO.setSearchscore(matching_score);
            ruleSession.insert(criminalRuleCDO);
            ruleSession.fireAllRules();

            if(StringUtils.isNoneBlank(criminalRuleCDO.getStatus())
                    && criminalRuleCDO.getStatus().equalsIgnoreCase("success")) {
                return true;
            }
            return false;
        } finally {
            ruleSession.dispose();
        }
    }
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
                    "  GROUP BY 2) a" +
                    " ";
            records = jdbcTemplate.queryForObject(sql,new Object[]{id}, String.class);
            return records;
        } catch (DataAccessException e) {
            records = " ";
            System.err.println("Error getProgram: " + e.getMessage());
        }
        return records;
    }
    /////////////////
    public String getAddress(Integer id) {
        String records = "";
        try {
            //String sql = "SELECT Address(?) as Address";
            String sql = "SELECT VALUE as typeText " +
                    " FROM `profile` a,`feature` b,`featureversion` c,`featureversionreference` d, " +
                    " `location` e,`locationpart` f,`locationpartvalue` g,`locparttype` h,`locationcountry` i " +
                    " WHERE a.`PrimaryKey`=b.`FK_Profile` AND b.`PrimaryKey`=c.`FK_Feature`  " +
                    " AND c.`ID`=d.`FeatureVersionID` AND d.`FK_Location`=e.`PrimaryKey`  " +
                    " AND f.`FK_Location`=e.`PrimaryKey`  AND g.`FK_LocationPart`=f.`PrimaryKey`  " +
                    " AND LocPartTypeID=h.`ID` AND a.`id`=? AND i.`FK_Location`=e.`PrimaryKey` LIMIT 1;";
            records = jdbcTemplate.queryForObject(sql,new Object[]{id}, String.class);

        } catch (EmptyResultDataAccessException e) {
            records = " ";
            System.err.println("Error getAddress: " + e.getMessage());
            //e.printStackTrace();
        }
        return records;
    }
    ////////////////
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
    public String getPartyType(Integer id) {
        try {
            //String sql = "SELECT `Type`(a.`PartySubTypeID`) as ptype FROM PROFILE a WHERE a.`ID`=?";
            String sql = "SELECT " +
                    " (SELECT IF(b.Text='Unknown',a.Text,b.Text) FROM partytype a,partysubtype b WHERE a.ID=b.PartyTypeID AND b.`ID`=PartySubTypeID) " +
                    " AS ptype FROM PROFILE a WHERE a.`ID`=?";
            String records = jdbcTemplate.queryForObject(sql,new Object[]{id}, String.class);
            return records;
        } catch (DataAccessException e) {
            System.err.println("Error getPartyType: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    ////////////////
}
