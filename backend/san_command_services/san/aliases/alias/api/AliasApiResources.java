package com.ponsun.san.aliases.alias.api;


import com.ponsun.san.aliases.alias.domain.Alias;
import com.ponsun.san.aliases.alias.request.CreateAliasRequest;
import com.ponsun.san.aliases.alias.request.UpdateAliasRequest;
import com.ponsun.san.aliases.alias.services.AliasReadPlatformService;
import com.ponsun.san.aliases.alias.services.AliasWritePlatformService;
import com.ponsun.san.infrastructure.utils.Response;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/Alias")
@Tag(name = "AliasApiResources")
public class AliasApiResources {
    private final AliasWritePlatformService aliasWritePlatformService;
    private final AliasReadPlatformService aliasReadPlatformService;
    @PostMapping("/CreateAliasRequest")
    public Response saveAlias(@RequestBody CreateAliasRequest createAliasRequest){
        Response response = this.aliasWritePlatformService.createAlias(createAliasRequest);
        return response;
    }
    @GetMapping
    public List<Alias> fetchAll(){ return this.aliasReadPlatformService.fetchAllAlias();}

    @GetMapping("/{id}")
    public Alias fetchAliasById(@PathVariable(name = "id")Integer id){
        return this.aliasReadPlatformService.fetchAliasById(id);
    }

    @PutMapping("/{id}")
    public Response updateAlias(@PathVariable Integer id, @RequestBody UpdateAliasRequest updateAliasRequest){
        Response response = this.aliasWritePlatformService.updateAlias(id,updateAliasRequest);
        return  response;
    }
}
