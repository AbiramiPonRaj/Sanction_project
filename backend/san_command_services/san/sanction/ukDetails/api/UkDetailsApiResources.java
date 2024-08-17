package com.ponsun.san.sanction.ukDetails.api;


import com.ponsun.san.sanction.ukDetails.data.UkAliasDetailsData;
import com.ponsun.san.sanction.ukDetails.data.UkCityDetailsData;
import com.ponsun.san.sanction.ukDetails.serices.UkDetailsReadPlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/UkDetails")
@Tag(name="UkDetailsApiResources")
public class UkDetailsApiResources {

    private final UkDetailsReadPlatformService ukDetailsReadPlatformService;
    @GetMapping("/UkAliasesDetails")
    public List<UkAliasDetailsData> fetchUkAliasDetailsData(@RequestParam Integer Group_ID) {
        return this.ukDetailsReadPlatformService.fetchAllUkAliasDetailsData(Group_ID);
    }

    @GetMapping("/UkCityDetails")
    public List<UkCityDetailsData> fetchUkCityDetailsData(@RequestParam Integer Group_ID) {
        return this.ukDetailsReadPlatformService.fetchAllUkCityDetailsData(Group_ID);
    }
}
