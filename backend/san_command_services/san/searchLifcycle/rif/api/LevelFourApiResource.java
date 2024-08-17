package com.ponsun.san.searchLifcycle.rif.api;

import com.ponsun.san.searchLifcycle.rif.data.LevelFourData;
import com.ponsun.san.searchLifcycle.rif.services.LevelFourWritePlatformService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/levelFour")
@CrossOrigin(origins = "http://localhost:3000")
public class LevelFourApiResource {
    private final LevelFourWritePlatformService levelFourWritePlatformService;

    public LevelFourApiResource(LevelFourWritePlatformService levelFourWritePlatformService){
        this.levelFourWritePlatformService = levelFourWritePlatformService;
    }
    @GetMapping
    public List<LevelFourData> fetchAll(@RequestParam Integer levelId,Integer levelIds)
    { return this.levelFourWritePlatformService.fetchAllLevelFourData(levelId,levelIds);}
}
