package com.ponsun.san.ofac.Aliases.api;

import com.ponsun.san.ofac.Aliases.data.AliasesData;
import com.ponsun.san.ofac.Aliases.services.AliasesWriteService;
import com.ponsun.san.ofac.identification.data.IdentificationData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/AliasesApiResources")
public class AliasesApiResources {
    private final AliasesWriteService aliasesWriteService;

    @GetMapping
    public List<AliasesData> fetchAll(@RequestParam Integer id){
        return this.aliasesWriteService.fetchAllAliasesData(id);
    }
}