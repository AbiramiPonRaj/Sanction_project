package com.ponsun.san.algorithm;

import com.ponsun.san.EhcachePOC.Data.OFACData;
import com.ponsun.san.dto.RecordDTO;
import com.ponsun.san.dto.SearchDTO;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.search.request.CreateSearchRequest;
import com.ponsun.san.searchLifcycle.search.services.SearchWritePlatformService;
import info.debatty.java.stringsimilarity.experimental.Sift4;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.time.LocalTime.now;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScoringCalculator {
    private final SearchWritePlatformService searchWritePlatformService ;
    private final ScoringCalculatorService scoringCalculatorService;

    Sift4 Sif    =new Sift4();
    @Transactional
    public List<RecordDTO> calculateScore(SearchDTO searchDTO, List<OFACData> countDataList) throws ExecutionException, InterruptedException {

        log.debug("StateDataList: {}", countDataList);
        List<RecordDTO> recordDTOS = new ArrayList<>();

        String queryCustomer    = searchDTO.getName();
        Double matching_score   = searchDTO.getMatching_score();

        ModelMapper modelMapper = new ModelMapper();
        CreateSearchRequest request = modelMapper.map(searchDTO, CreateSearchRequest.class);
        Response response   = this.searchWritePlatformService.createSearch(request);
        Integer searchId    = (Integer) response.getId();
//trace 1
        log.info("Fn Start"+now());

        //stream option 2
        // Call the asynchronous method
        List<RecordDTO> listOfArrays  = this.scoringCalculatorService.calculatenamewiseScore(queryCustomer,matching_score, searchId,countDataList).get();// Wait for the result and get the list
        log.info("Fn end"+now());

        //treeset remove sort option 1
        Collections.sort(listOfArrays, Comparator.comparingDouble(RecordDTO::getScore));//
        log.info("Fn sort call"+now());
        //log.info("listOfArrays"+listOfArrays);
//10 name1 - 25
//10 name2 -45
//10 namen  - 65 max
        ///////////
        Map<Integer, RecordDTO> map = new HashMap<>();
        for (RecordDTO array : listOfArrays) {
            int uniqueValue = array.getIds();
            map.put(uniqueValue, array);
        }///slow
        log.info("Fn unique  call"+now());

//trace 2

        List<RecordDTO> uniqueListOfArrays = new ArrayList<>(map.values());// List<RecordDTO> finalrecordDTOS = new ArrayList<>();
        //System.out.println(uniqueListOfArrays);
//        recordDTOS  = scoringCalculatorService.getRecordDetails(searchId,uniqueListOfArrays).get();
        recordDTOS  = scoringCalculatorService.processAndSaveRecords(uniqueListOfArrays,searchId);
        //////////

        Collections.sort(recordDTOS, Comparator.comparingDouble(RecordDTO::getScore).reversed());
        return recordDTOS;
    }
    ////////////////
}
