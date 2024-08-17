package com.ponsun.san.sanction.unDetails.api;

import com.ponsun.san.sanction.unDetails.data.*;
import com.ponsun.san.sanction.unDetails.services.UnDetailsReadService;
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
@RequestMapping("api/v1/UnDetails")
@Tag(name="UN_DetailsApiResource")
public class UnDetailsApiResources {

    private final UnDetailsReadService unDetailsReadService;

    @GetMapping("/UnAliasDetails")
    public List<UnAliasDetailsData> fetchAliasDetails(@RequestParam Integer DATAID) {
        return unDetailsReadService.fetchAliasDetails(DATAID);
    }
    @GetMapping("/UnDesignationDetails")
    public List<UnDesignationDetailsData> fetchDesignationDetails(@RequestParam Integer DATAID) {
        return unDetailsReadService.fetchDesignationDetails(DATAID);
    }

    @GetMapping("/UnDetails")
    public UnPersonalDetailsData fetchAllDetailsData(@RequestParam Integer DATAID) {
        return this.unDetailsReadService.fetchAllDetailsData(DATAID);
    }


}
