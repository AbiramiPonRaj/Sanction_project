package com.ponsun.san.searchLifcycle.hitrecordtable.api;
import com.ponsun.san.searchLifcycle.hitrecordtable.data.HitRecordDataTableData;
import com.ponsun.san.searchLifcycle.hitrecordtable.services.HitRecordTableWritePlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/hitdatatable")
@CrossOrigin(origins = "http://localhost:3000")
public class HitRecordTableApiResource {
    private final HitRecordTableWritePlatformService hitRecordTableWritePlatformService;
    @GetMapping
    public List<HitRecordDataTableData> fetchAll(@RequestParam String levelId){
        return this.hitRecordTableWritePlatformService.fetchAllHitDataTableData(levelId);
    }
}