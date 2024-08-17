package com.ponsun.san.uiTest;

import com.ponsun.san.uiTest.AlgorithmTesting.ExactMatch.ExactMatch;

import com.ponsun.san.uiTest.AlgorithmTesting.Fuzzy.Fuzzy_WeightedRatio;
import com.ponsun.san.uiTest.AlgorithmTesting.FuzzyDMCosine.Fuzzy_DM_Cosine;
import com.ponsun.san.uiTest.AlgorithmTesting.FuzzySoundex.Fuzzy_Soundex;
import com.ponsun.san.uiTest.AlgorithmTesting.Jaro.Jarowinkler_Match;
import com.ponsun.san.uiTest.AlgorithmTesting.Oneside.Oneside;
import com.ponsun.san.uiTest.dto.UiReciveRecord;
import com.ponsun.san.uiTest.dto.UiSearchDtoVerify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UiAlgorithemVerification  {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public List<UiReciveRecord> uicalculateScore(UiSearchDtoVerify searchDtoVerify)
    {
        String firstName    = searchDtoVerify.getFirstName().toLowerCase().replaceAll(",", " ").replaceAll("[^-+a-zA-Z0-9\\s]", "");
        String secondName   = searchDtoVerify.getSecondName().toLowerCase().replaceAll(",", " ").replaceAll("[^-+a-zA-Z0-9\\s]", "");
//.replaceAll("[-+^]*", " ")
//        firstName=firstName.replaceAll(",", " ");
//        firstName=firstName.replaceAll("[^a-zA-Z0-9\\s]", "");
//
//        secondName=secondName.replaceAll(",", " ");
//        secondName=secondName.replaceAll("[^a-zA-Z0-9\\s]", "");

        double onside = 0;
        try {
            onside = Oneside.onesideMatching(firstName, secondName);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("onside"+e);
        }
        double exactMatch   = 0;
        try {
            exactMatch = ExactMatch.ExactMatching(firstName, secondName);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("exactMatch"+e);
        }
        double jaro80           = Jarowinkler_Match.getJarowinklerMatching(firstName,secondName);
//        double ratio            = Fuzzy_TokensetRatio.Fuzzy_TokensetRatio(firstName,secondName);
//        double tokenSet         = Fuzzy_TokensetRatio.Fuzzy_TokensetRatio(firstName,secondName);
//        double tokenSort        = Fuzzy_TokenSortRatio.Fuzzy_TokenSortRatio(firstName,secondName);
        double tokenWeight      = Fuzzy_WeightedRatio.Fuzzy_WeightedRatio(firstName,secondName);

        double jaroWinkler      = Jarowinkler_Match.getJarowinklerMatching(firstName,secondName);
//        double jaroWinkler_2    = Jarowinkler_Match_2.getJarowinklerMatching_2(firstName,secondName);
//        double jaroWinkler_3    = Jarowinkler_Match_3.getJarowinklerMatching_3(firstName,secondName);
//      int weightRatio       = weightedRatio(firstName,secondName);
        double soundxmatch = 0;
        try {
            soundxmatch = Fuzzy_Soundex.getFuzzySoundx(firstName, secondName);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("FuzzySoundx"+e);
        }
        double fuzzyDmCosine = 0;
        try {
            fuzzyDmCosine = Fuzzy_DM_Cosine.getFuzzy_DM_Cosine(firstName, secondName);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("DM_Cosine"+e);
        }
//        dm.setMaxCodeLen(1000);
//        String dmvalue1         = dm.doubleMetaphone(firstName);
//        String dmvalue2         = dm.doubleMetaphone(secondName);
//        double dmcosineValue    = round(cosine.similarity(dmvalue1, dmvalue2) * 100);
//        double damroSimilarity  = (1-(dam.distance(firstName,secondName)/max(length(firstName),length(secondName))))*100;
//
//        double jaroWinkler      = round(jaroWinklerSimilarity.apply(firstName,secondName)*100);
//        double jaroWinklerold   = round(jw.similarity(firstName,secondName)*100);

//        String jaroSQL          = calculateJaroWinklerSimilarity(firstName,secondName);

        List<UiReciveRecord> uirecordDTOS = new ArrayList<>();
        UiReciveRecord uiRecordDTO = new UiReciveRecord();
        uiRecordDTO.setOnesidematching(onside);
        uiRecordDTO.setTwosidematching(exactMatch);
        uiRecordDTO.setJaro(jaro80);
//        uiRecordDTO.setRatio(ratio);
//        uiRecordDTO.setTokenSet(tokenSet);
//        uiRecordDTO.setTokenSort(tokenSort);
        uiRecordDTO.setTokenWeight(tokenWeight);

//        uiRecordDTO.setDamerau(Damerau);
//        uiRecordDTO.setWeighted(weighted);
//        uiRecordDTO.setFuzzyDamerau(fuzzyDamerau);
//        uiRecordDTO.setFuzzyWeighted(fuzzyWeighted);
//        uiRecordDTO.setCheckNew(CheckNew);

//        uiRecordDTO.setTokenDoubleMetaphoneCosine(doubleMetaphone);
//        uiRecordDTO.setTokenFuzzyWeightRatio(TokenWeightRatio);
//        uiRecordDTO.setTokenJaro(TokenJaro);

//        uiRecordDTO.setSoundxmatching(soundxmatch);
//        uiRecordDTO.setJaro_3(jaroWinkler_3);
//        uiRecordDTO.setJaro(jaroWinkler);
//        uiRecordDTO.setJaro_2(jaroWinkler_2);
//        uiRecordDTO.setFuzzySoundx(soundxmatch);
//        uiRecordDTO.setFuzzydouble_Metaphone_cosine(fuzzyDmCosine);

        uirecordDTOS.add(uiRecordDTO);
        return uirecordDTOS;
    }
    public String calculateJaroWinklerSimilarity(String str1, String str2) {
        String sql = "SELECT round((jaro_winkler_similarity(?, ?)*100),0) AS similarity";
        String similarity = jdbcTemplate.queryForObject(sql, new Object[]{str1, str2}, String.class);
        return similarity;
    }
    public Double fuzzywuzzySimilarityScore(String s1,String s2)
    {
//      String s1 = "ibrahimdawood ";
//      String s2 = " dawood ibrahim";

        String[] parts1 = s1.split(" ");
        String[] parts2 = s2.split(" ");

        ArrayList<Double> score = new ArrayList<>();
        for (int i = 0; i < parts1.length; i++) {
            for (int j = 0; j < parts2.length; j++) {
                score.add((double) FuzzySearch.weightedRatio(parts1[i], parts2[j]));
            }
        }
        return  Collections.max(score);
    }
}
