package com.ponsun.san.searchLifcycle.hitrecordlifecycle.api;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain.HitRecordLifecycle;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.request.CreateHitRecordLifecycleRequest;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.request.UpdateHitdataLifecycleRequest;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.services.HitRecordLifecycleReadPlatformService;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.services.HitRecordLifecycleWritePlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/hitrecordlifecycle")
@Tag(name="hitrecordlifecycleApiResource")
@CrossOrigin(origins = "http://localhost:3000")
public class hitrecordlifecycleApiResource {
    private  final HitRecordLifecycleWritePlatformService hitRecordLifecycleWritePlatformService;
    private final HitRecordLifecycleReadPlatformService hitRecordLifecycleReadPlatformService;

    @PostMapping("/createhitrecordlifecycle")
    public Response savehitdatalifecycle(@RequestBody CreateHitRecordLifecycleRequest createHitRecordLifecycleRequest){
        Response response = this.hitRecordLifecycleWritePlatformService.createHitdataLifecycle(createHitRecordLifecycleRequest);
        return response;
    }

    @GetMapping
    public List<HitRecordLifecycle> fetchAll() {
        return this.hitRecordLifecycleReadPlatformService.fetchAllHitdataLifecycle();}

    @GetMapping("/{id}")
    public HitRecordLifecycle fetchHitdataLifecycleById(@PathVariable (name = "id") Integer id) {
        return this.hitRecordLifecycleReadPlatformService.fetchHitdataLifecycleById(id);
    }
    @PutMapping("/{id}")
    public Response updateHitdataLifecycle(@PathVariable Integer id, @RequestBody UpdateHitdataLifecycleRequest updateHitdataLifecycleRequest) {
        Response response = this.hitRecordLifecycleWritePlatformService.updateHitdataLifecycle(id, updateHitdataLifecycleRequest);
        return response;
    }

    @PutMapping("/{id}/block")
    public Response blockHitdataLifecycle(@PathVariable Integer id) {
        Response response = this.hitRecordLifecycleWritePlatformService.blockHitdataLifecycle(id);
        return response;
    }

    @PutMapping("/{id}/unblock")
    public Response unblockHitdataLifecycle(@PathVariable Integer id) {
        Response response = this.hitRecordLifecycleWritePlatformService.unblockHitdataLifecycle(id);
        return response;
    }
}
