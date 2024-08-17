package com.ponsun.san.ofac.Details.api;

import com.ponsun.san.ofac.Aliases.data.AliasesData;
import com.ponsun.san.ofac.Details.data.DetailsData;
import com.ponsun.san.ofac.Details.services.DetailsWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/DetailsApiRresources")
public class DetailsApiResources {
    private final DetailsWriteService detailsWriteService;

    @GetMapping
    public List<DetailsData> fetchAll(@RequestParam Integer id){
        return this.detailsWriteService.fetchAllDetailsData(id);
    }

}
