package com.ponsun.san.searchLifcycle.hitrecord.api;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecord;
import com.ponsun.san.searchLifcycle.hitrecord.request.CreateHitRecordRequest;
import com.ponsun.san.searchLifcycle.hitrecord.request.UpdateHitRecordRequest;
import com.ponsun.san.searchLifcycle.hitrecord.services.HitRecordReadPlatformService;
import com.ponsun.san.searchLifcycle.hitrecord.services.HitRecordWritePlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/HitRecord")
@Tag(name = "HitRecordApiResource")
@CrossOrigin(origins = "http://localhost:3000")

public class HitRecordApiResource {

    private final HitRecordWritePlatformService hitRecordWritePlatformService;

    private final HitRecordReadPlatformService hitRecordReadPlatformService;

    @PostMapping("/createHitData")
    public Response saveHitData (@RequestBody CreateHitRecordRequest createHitDataRequest) {
        Response response = this.hitRecordWritePlatformService.createHitData(createHitDataRequest);
        return response;
    }
    @GetMapping
    public List<HitRecord> fetchAll() {
        return this.hitRecordReadPlatformService.fetchAll();
    }

    @GetMapping("/{id}")
    public HitRecord fetchAllHitDataById(@PathVariable (name = "id")Integer id){
        return this.hitRecordReadPlatformService.fetchAHitDataById(id);
    }
    @PutMapping("/{id}")
    public Response updateHitData(@PathVariable Integer id, @RequestBody UpdateHitRecordRequest updateHitDataRequest) {
        Response response = this.hitRecordWritePlatformService.updateHitdata(id, updateHitDataRequest);
        return response;
    }

    @PutMapping("/{id}/block")
    public Response blockHitData(@PathVariable Integer id) {
        Response response = this.hitRecordWritePlatformService.blockHitData(id);
        return response;
    }

    @PutMapping("/{id}/unblock")
    public Response unblockHitData(@PathVariable Integer id) {
        Response response = this.hitRecordWritePlatformService.unblockHitData(id);
        return response;
    }
}
