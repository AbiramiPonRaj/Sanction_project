package com.ponsun.san.searchLifcycle.insert.api;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.PendingCase.data.PendingCaseData;
import com.ponsun.san.searchLifcycle.hitcase.request.CreateHitCaseRequest;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.services.HitRecordLifecycleWritePlatformService;
import com.ponsun.san.searchLifcycle.insert.services.InsertWritePlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/insert")
@CrossOrigin(origins = "http://localhost:3000")
public class InsertApiResource {

    private final InsertWritePlatformService insertWritePlatformService;
    private  final HitRecordLifecycleWritePlatformService hitRecordLifecycleWritePlatformService;
//    @PostMapping("/SetStatusHitdataLifeCycle")
//    public Response L2_savehitdatalifecycle(@RequestBody CreateHitdataLifecycle createHitdataLifecycle){
//        Response response = this.hitdataLifecycleWritePlatformService.l2_createHitdataLifecycle(createHitdataLifecycle);
//        return response;
//    }
    @PostMapping("CreateHitCase")
    public Response saveHitCase(@RequestBody CreateHitCaseRequest createHitCaseRequest) {
        Response response = this.insertWritePlatformService.CreateHitCase_HitrecordLifeCycle(createHitCaseRequest);
        return response;
    }
    @PostMapping("/CaseLifeCycleImpl")
    public ResponseEntity<String> insertData(@RequestBody PendingCaseData pendingCaseData) {
        try {
            insertWritePlatformService.insertPendingCaseData(pendingCaseData);
            return new ResponseEntity<>("Data inserted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to insert data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
