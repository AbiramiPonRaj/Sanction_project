

package com.ponsun.san.ofac.liststatus.api;
import com.ponsun.san.ofac.Program.data.ProgramData;
import com.ponsun.san.ofac.Program.services.ProgramWriteService;
import com.ponsun.san.ofac.liststatus.data.ListStatusData;
import com.ponsun.san.ofac.liststatus.services.ListStatusWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/ListStatusApiResources")
public class ListStatusApiResources {
    private final ListStatusWriteService listStatusWriteService;

    @GetMapping
    public List<ListStatusData> fetchAll(@RequestParam String id){
        return this.listStatusWriteService.fetchAlllistStatus(id);
    }

}
