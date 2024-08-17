package com.ponsun.san.searchLifcycle.hitcase.api;


import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.hitcase.domain.Hitcase;
import com.ponsun.san.searchLifcycle.hitcase.request.CreateHitCaseRequest;
import com.ponsun.san.searchLifcycle.hitcase.request.UpdateHitCaseRequest;
import com.ponsun.san.searchLifcycle.hitcase.services.HitCaseReadPlatformService;
import com.ponsun.san.searchLifcycle.hitcase.services.HitCaseWritePlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/hitcase")
@Tag(name = "HitCaseApiResource")
@CrossOrigin(origins = "http://localhost:3000")
public class HitCaseApiResource {

    private final HitCaseWritePlatformService hitCaseWritePlatformService;
    private  final HitCaseReadPlatformService hitCaseReadPlatformService;

    @PostMapping("/CreateHitcase")
    public Response saveHitCase(@RequestBody CreateHitCaseRequest createHitCaseRequest){
        Response response = this.hitCaseWritePlatformService.CreateHitcase(createHitCaseRequest);
        return response;
    }

    @GetMapping
    public List<Hitcase> fetchAll() {
        return this.hitCaseReadPlatformService.fetchAllHitcase();
    }

    @GetMapping("/{id}")
    public Hitcase fetchHitcaseById(@PathVariable (name = "id") Integer id) {
        return this.hitCaseReadPlatformService.fetchHitcaseById(id);
    }
    @PutMapping("/{id}")
    public Response updateHitcase(@PathVariable Integer id, @RequestBody UpdateHitCaseRequest updateHitCaseRequest) {
        Response response = this.hitCaseWritePlatformService.updateHitcase(id, updateHitCaseRequest);
        return response;
    }

    @PutMapping("/{id}/block")
    public Response blockHitcase(@PathVariable Integer id) {
        Response response = this.hitCaseWritePlatformService.blockHitcase(id);
        return response;
    }

    @PutMapping("/{id}/unblock")
    public Response unblockHitcase(@PathVariable Integer id) {
        Response response = this.hitCaseWritePlatformService.unblockHitcase(id);
        return response;
    }
}
