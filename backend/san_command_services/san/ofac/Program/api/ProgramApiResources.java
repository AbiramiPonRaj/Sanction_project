//package com.ponsun.san.ofac.Program.api;
//
//public class ProgramApiResources {
//}


package com.ponsun.san.ofac.Program.api;
import com.ponsun.san.ofac.Program.data.ProgramData;
import com.ponsun.san.ofac.Program.services.ProgramWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/ProgramApiResources")
public class ProgramApiResources {
    private final ProgramWriteService programWriteService;

    @GetMapping
    public List<ProgramData> fetchAll(@RequestParam String id){
        return this.programWriteService.fetchAllProgramData(id);
    }

}
