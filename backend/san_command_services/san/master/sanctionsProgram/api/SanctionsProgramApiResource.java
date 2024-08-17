package com.ponsun.san.master.sanctionsProgram.api;



import com.ponsun.san.infrastructure.utils.Response;


import com.ponsun.san.master.sanctionsProgram.domain.SanctionsProgram;
import com.ponsun.san.master.sanctionsProgram.request.CreateSanctionsProgramRequest;
import com.ponsun.san.master.sanctionsProgram.request.UpdateSanctionsProgramRequest;
import com.ponsun.san.master.sanctionsProgram.services.SanctionsProgramReadPlatformService;
import com.ponsun.san.master.sanctionsProgram.services.SanctionsProgramWritePlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/SanctionsProgram")
@Tag(name = "SanctionsProgramApiResources")
public class SanctionsProgramApiResource {
    private final SanctionsProgramWritePlatformService sanctionsProgramWritePlatformService;
    private final SanctionsProgramReadPlatformService sanctionsProgramReadPlatformService;
    @PostMapping("/CreateSanctionsProgramRequest")
    public Response saveSanctionsProgram(@RequestBody CreateSanctionsProgramRequest createSanctionsProgramRequest){
        Response response = this.sanctionsProgramWritePlatformService.createSanctionsProgram(createSanctionsProgramRequest);
        return response;
    }
    @GetMapping
    public List<SanctionsProgram> fetchAll(){ return this.sanctionsProgramReadPlatformService.fetchAllSanctionsProgram();}

    @GetMapping("/{id}")
    public SanctionsProgram fetchSanctionsProgramById(@PathVariable(name = "id")Integer id){
        return this.sanctionsProgramReadPlatformService.fetchSanctionsProgramById(id);
    }

    @PutMapping("/{id}")
    public Response updateSanctionsProgram(@PathVariable Integer id, @RequestBody UpdateSanctionsProgramRequest updateSanctionsProgramRequest){
        Response response = this.sanctionsProgramWritePlatformService.updateSanctionsProgram(id,updateSanctionsProgramRequest);
        return  response;
    }
}
