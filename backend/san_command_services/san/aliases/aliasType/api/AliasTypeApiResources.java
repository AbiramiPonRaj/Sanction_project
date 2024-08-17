package com.ponsun.san.aliases.aliasType.api;



import com.ponsun.san.aliases.aliasType.domain.AliasType;
import com.ponsun.san.aliases.aliasType.request.CreateAliasTypeRequest;
import com.ponsun.san.aliases.aliasType.request.UpdateAliasTypeRequest;
import com.ponsun.san.aliases.aliasType.services.AliasTypeReadPlatformService;
import com.ponsun.san.aliases.aliasType.services.AliasTypeWritePlatformService;
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
@RequestMapping("/api/v1/AliasType")
@Tag(name = "AliasTypeApiResources")
public class AliasTypeApiResources {
    private final AliasTypeWritePlatformService aliasTypeWritePlatformService;
    private final AliasTypeReadPlatformService aliasTypeReadPlatformService;
    @PostMapping("/CreateAliasTypeRequest")
    public Response saveAliasType(@RequestBody CreateAliasTypeRequest createAliasTypeRequest){
        Response response = this.aliasTypeWritePlatformService.createAliasType(createAliasTypeRequest);
        return response;
    }
    @GetMapping
    public List<AliasType> fetchAll(){ return this.aliasTypeReadPlatformService.fetchAllAliasType();}

    @GetMapping("/{id}")
    public AliasType fetchAliasTypeById(@PathVariable(name = "id")Integer id){
        return this.aliasTypeReadPlatformService.fetchAliasTypeById(id);
    }

    @PutMapping("/{id}")
    public Response updateAliasType(@PathVariable Integer id, @RequestBody UpdateAliasTypeRequest updateAliasTypeRequest){
        Response response = this.aliasTypeWritePlatformService.updateAliasType(id,updateAliasTypeRequest);
        return  response;
    }
}
