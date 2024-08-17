package com.ponsun.san.searchLifcycle.LevelTwoReview.api;


import com.ponsun.san.searchLifcycle.LevelTwoReview.data.LevelTwoReviewData;
import com.ponsun.san.searchLifcycle.LevelTwoReview.services.LevelTwoReviewWritePlatformService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/LevelTwoReview")
@CrossOrigin(origins = "https://localhost:3000")
public class LevelTwoReviewApiResource {
    private final LevelTwoReviewWritePlatformService levelTwoReviewWritePlatformService;

    public LevelTwoReviewApiResource(LevelTwoReviewWritePlatformService levelTwoReviewWritePlatformService){
        this.levelTwoReviewWritePlatformService = levelTwoReviewWritePlatformService;
    }
    @GetMapping
    public List<LevelTwoReviewData> fetchAll(){return this.levelTwoReviewWritePlatformService.fetchAllLevelTwoReviewData();}
}
