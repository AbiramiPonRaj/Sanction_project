package com.ponsun.san.ofac.lookup.api;
import com.ponsun.san.ofac.lookup.data.LookUpData;
import com.ponsun.san.ofac.lookup.services.LookupWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/LookupApiRresources")
public class LookupApiRresources {
    private final LookupWriteService lookupWriteService;

    @GetMapping
    public List<LookUpData> fetchAll(){return this.lookupWriteService.fetchAllLookUpData();}

}

