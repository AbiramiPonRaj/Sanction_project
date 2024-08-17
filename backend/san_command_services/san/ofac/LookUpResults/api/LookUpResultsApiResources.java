package com.ponsun.san.ofac.LookUpResults.api;
import com.ponsun.san.ofac.LookUpResults.data.LookUpResultsData;
import com.ponsun.san.ofac.LookUpResults.services.LookUpResultsWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/LookUpResultsApiResources")
public class LookUpResultsApiResources {
    private final LookUpResultsWriteService lookUpResultsWriteService;

    @GetMapping
    public List<LookUpResultsData> fetchAll(@RequestParam String name){
        return this.lookUpResultsWriteService.fetchAllLookUpResultsData(name);
    }

}
