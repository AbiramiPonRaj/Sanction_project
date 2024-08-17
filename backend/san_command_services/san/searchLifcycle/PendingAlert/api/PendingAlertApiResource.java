package com.ponsun.san.searchLifcycle.PendingAlert.api;

import com.ponsun.san.searchLifcycle.PendingAlert.data.PendingAlertData;
import com.ponsun.san.searchLifcycle.PendingAlert.services.PendingAlertWritePlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/pendingalert")
@CrossOrigin(origins = "http://localhost:3000")
public class PendingAlertApiResource {
    private final PendingAlertWritePlatformService pendingAlertWritePlatformService;

    @GetMapping
    public List<PendingAlertData> fetchAll(@RequestParam String levelId){
        return this.pendingAlertWritePlatformService.fetchAllPendingAlertData(levelId);
    }
}
