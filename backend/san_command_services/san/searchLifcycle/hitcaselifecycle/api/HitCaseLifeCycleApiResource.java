package com.ponsun.san.searchLifcycle.hitcaselifecycle.api;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.domain.HitcaseLifecycle;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.request.CreateHitCaseLifeCycleRequest;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.request.UpdateHitCaseLifeCycleRequest;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.services.HitCaseLifeCycleReadPlatformService;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.services.HitCaseLifeCycleWritePlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/hitcaselifecycle")
@Tag(name = "HitCaseLifeCycleApiResource")
public class HitCaseLifeCycleApiResource {
    private final HitCaseLifeCycleWritePlatformService hitCaseLifeCycleWritePlatformService;

    private final HitCaseLifeCycleReadPlatformService hitCaseLifeCycleReadPlatformService;

    @PostMapping("/CreateHitCaseLifeCycleRequest")
    public Response saveUser(@RequestBody CreateHitCaseLifeCycleRequest createHitCaseLifeCycleRequest) {
        Response response = this.hitCaseLifeCycleWritePlatformService.createHitCaseLifeCycle(createHitCaseLifeCycleRequest);
        return response;
    }

    @GetMapping
    public List<HitcaseLifecycle> fetchAll() {
        return this.hitCaseLifeCycleReadPlatformService.fetchAllHitcaseLife();
    }

    @GetMapping("/{id}")
    public HitcaseLifecycle fetchUserById(@PathVariable (name = "id") Integer id) {
        return this.hitCaseLifeCycleReadPlatformService.fetchHitcaseLifeById(id);
    }
    @PutMapping("/{id}")
    public Response updateHitCaseLifeCycle(@PathVariable Integer id, @RequestBody UpdateHitCaseLifeCycleRequest updateHitCaseLifeCycleRequest) {
        Response response = this.hitCaseLifeCycleWritePlatformService.updateHitCaseLifeCycle(id, updateHitCaseLifeCycleRequest);
        return response;
    }

    @PutMapping("/{id}/block")
    public Response blockHitCaseLifeCycle(@PathVariable Integer id) {
        Response response = this.hitCaseLifeCycleWritePlatformService.blockHitCaseLifeCycle(id);
        return response;
    }

    @PutMapping("/{id}/unblock")
    public Response unblockHitCaseLifeCycle(@PathVariable Integer id) {
        Response response = this.hitCaseLifeCycleWritePlatformService.unblockHitCaseLifeCycle(id);
        return response;
    }
}
