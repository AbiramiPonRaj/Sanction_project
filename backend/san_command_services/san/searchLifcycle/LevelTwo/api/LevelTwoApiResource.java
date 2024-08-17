package com.ponsun.san.searchLifcycle.LevelTwo.api;

import com.ponsun.san.searchLifcycle.LevelTwo.data.LevelTwoData;
import com.ponsun.san.searchLifcycle.LevelTwo.services.LevelTwoWritePlatformService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("api/v1/levelTwo")
@CrossOrigin(origins = "http://localhost:3000")
public class LevelTwoApiResource {
   private final LevelTwoWritePlatformService levelTwoWritePlatformService;

   public LevelTwoApiResource(LevelTwoWritePlatformService levelTwoWritePlatformService){
       this.levelTwoWritePlatformService = levelTwoWritePlatformService;
   }
   @GetMapping
    public List<LevelTwoData> fetchAll(){
       return this.levelTwoWritePlatformService.fetchAllLevelTwoData();
   }

}
