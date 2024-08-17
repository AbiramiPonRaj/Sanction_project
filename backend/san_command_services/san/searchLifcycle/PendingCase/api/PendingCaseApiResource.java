package com.ponsun.san.searchLifcycle.PendingCase.api;

import com.ponsun.san.searchLifcycle.PendingCase.data.PendingCaseData;
import com.ponsun.san.searchLifcycle.PendingCase.services.PendingCaseWritePlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/pendingcase")
public class PendingCaseApiResource {
    private final PendingCaseWritePlatformService pendingCaseWritePlatformService;

    @GetMapping("/l3PendingCase")
    public List<PendingCaseData> fetchAll(){
        return this.pendingCaseWritePlatformService.fetchAllPendingCaseData();
    }
    //L4 Pending Case
    @GetMapping("/l4PendingCase")
    public List<PendingCaseData> fetchByPendingCase(){
        return this.pendingCaseWritePlatformService.fetchByLfourPendingCase();
    }

}
