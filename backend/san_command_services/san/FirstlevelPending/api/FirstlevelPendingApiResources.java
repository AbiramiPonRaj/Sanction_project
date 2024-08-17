package com.ponsun.san.FirstlevelPending.api;
import com.ponsun.san.FirstlevelPending.data.FirstlevelPendingData;
import com.ponsun.san.FirstlevelPending.service.FirstlevelPendingReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/FirstlevelPending")
public class FirstlevelPendingApiResources {
    private final FirstlevelPendingReadService firstlevelPendingReadService;

    @GetMapping
    public List<FirstlevelPendingData> fetchAll(@RequestParam Integer id){
        return this.firstlevelPendingReadService.fetchAllPendingData(id);
    }
}
