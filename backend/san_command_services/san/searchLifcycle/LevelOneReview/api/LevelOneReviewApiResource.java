package com.ponsun.san.searchLifcycle.LevelOneReview.api;

import com.ponsun.san.searchLifcycle.LevelOneReview.data.LevelOneReviewData;
import com.ponsun.san.searchLifcycle.LevelOneReview.services.LevelOneReviewWritePlatformService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("api/v1/LevelOneReview")
@CrossOrigin(origins = "http://localhost:3000")
public class LevelOneReviewApiResource {

    private final LevelOneReviewWritePlatformService levelOneReviewWritePlatformService;

    public LevelOneReviewApiResource(LevelOneReviewWritePlatformService levelOneReviewWritePlatformService){
        this.levelOneReviewWritePlatformService = levelOneReviewWritePlatformService;
    }
    @GetMapping
    public List<LevelOneReviewData> fetchAll() { return this.levelOneReviewWritePlatformService.fetchAllLevelOneReviewData();}

}
