package com.ponsun.san.master.all.api;


import com.ponsun.san.master.all.data.AllData;
import com.ponsun.san.master.all.services.AllReadPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/All")
public class AllApiResources {

    private  final AllReadPlatformService allReadPlatformService;

    @GetMapping
    public List<AllData> fetchAll(){
        return this.allReadPlatformService.fetchAllAllData();}
}
