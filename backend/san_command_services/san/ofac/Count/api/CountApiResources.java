package com.ponsun.san.ofac.Count.api;

import com.ponsun.san.dto.RecordDTO;
import com.ponsun.san.dto.SearchDTO;
import com.ponsun.san.ofac.Count.data.SanctionDetailData;
import com.ponsun.san.ofac.Count.service.CountReadService;
import com.ponsun.san.ofac.Count.service.RecordDetails;
import com.ponsun.san.ofac.Count.service.RecordReadService;
import com.ponsun.san.ofac.Count.service.SanctionDetailReadService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.Count;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/Count")
@Tag(name = "CountApiResources")
public class CountApiResources {
    private final CountReadService countReadService;
    private final RecordDetails recDet;
    private final SanctionDetailReadService sanctionDetailReadService;
    private final RecordDetails recordDetails;

    @GetMapping("/{id}")
    public List<Count> fetchAll(@RequestParam Integer id){
        return this.countReadService.fetchAllCount(id);
    }
    @GetMapping("/RecordsCount")
    public List<RecordDTO> getRecords(@RequestBody SearchDTO searchDTO){
        return this.countReadService.getRecordsCount(searchDTO);
    }
//    @GetMapping("/fetch")
//    public Map<String, SanctionDetailData> fetchSanctionData(
//            @RequestParam(required = false) Integer entityLogicalId,
//            @RequestParam(required = false) Integer groupId,
//            @RequestParam(required = false) Integer dataId) {
//        return sanctionDetailReadService.fetchSanctionData(entityLogicalId, groupId, dataId);
//    }

    @GetMapping("/fetchEuSanctionData")
    public SanctionDetailData fetchEuSanctionData(@RequestParam Integer entityLogicalId) {
        return sanctionDetailReadService.fetchEuSanctionData(entityLogicalId);
    }


    @GetMapping("/eu")
    public List<SanctionDetailData> fetchAllEUSanctionData() {
        return sanctionDetailReadService.fetchAllEUSanctionData();
    }
    @GetMapping("/uk")
    public List<SanctionDetailData>  fetchAllUKSanctionData() {
        return sanctionDetailReadService.fetchAllUKSanctionData();
    }
    @GetMapping("/un")
    public List<SanctionDetailData> fetchAllUnSanctionApiData() {
        return sanctionDetailReadService.fetchAllUnSanctionData();
    }

    @PostMapping("/set")
    public void setCacheData() {
        recordDetails.setCacheData();
    }


//    @GetMapping("/GetRecordsDet")
//    public List<Integer> getRecords(){
//        return this.recDet.getliststatus();
//    }
}
