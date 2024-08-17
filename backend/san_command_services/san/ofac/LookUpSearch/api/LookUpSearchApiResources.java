package com.ponsun.san.ofac.LookUpSearch.api;
import com.ponsun.san.ofac.LookUpSearch.data.LookUpSearchData;
import com.ponsun.san.ofac.LookUpSearch.services.LookUpSearchWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/LookUpSearchApiResources")
public class LookUpSearchApiResources {
    private final LookUpSearchWriteService lookUpSearchWriteService;

    @GetMapping
    public List<LookUpSearchData> fetchAll(){
        return this.lookUpSearchWriteService.fetchAllLookUpSearchData();
    }

}
