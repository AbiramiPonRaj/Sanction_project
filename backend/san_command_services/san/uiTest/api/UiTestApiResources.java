package com.ponsun.san.uiTest.api;

import com.ponsun.san.uiTest.dto.UiReciveRecord;
import com.ponsun.san.uiTest.dto.UiRecordDTO;
import com.ponsun.san.uiTest.dto.UiSearchDTO;
import com.ponsun.san.uiTest.dto.UiSearchDtoVerify;
import com.ponsun.san.uiTest.service.UiTestReadService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/UiTestApiResources")
@Tag(name = "UiTestApiResources")
public class UiTestApiResources {
    private final UiTestReadService uiTestReadService;
    @GetMapping("/UiTestRecords")
    public List<UiRecordDTO> UiTestRecords(@RequestBody UiSearchDTO uiSearchDTO){
        return this.uiTestReadService.getuiTestRecords(uiSearchDTO);
    }
    @GetMapping("/UiTestAlgorithemRecords")
    public List<UiReciveRecord> UiTestAlgorithemRecords(@RequestBody UiSearchDtoVerify uiSearchDtoVerify){
        return this.uiTestReadService.getUiRecords(uiSearchDtoVerify);
    }
}