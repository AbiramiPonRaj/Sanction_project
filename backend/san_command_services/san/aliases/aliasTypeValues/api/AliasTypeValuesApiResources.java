package com.ponsun.san.aliases.aliasTypeValues.api;



import com.ponsun.san.aliases.aliasTypeValues.domain.AliasTypeValues;
import com.ponsun.san.aliases.aliasTypeValues.request.CreateAliasTypeValuesRequest;
import com.ponsun.san.aliases.aliasTypeValues.request.UpdateAliasTypeValuesRequest;
import com.ponsun.san.aliases.aliasTypeValues.services.AliasTypeValuesReadPlatformService;
import com.ponsun.san.aliases.aliasTypeValues.services.AliasTypeValuesWritePlatformService;
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
@RequestMapping("/api/v1/AliasTypeValues")
@Tag(name = "AliasTypeValuesApiResources")
public class AliasTypeValuesApiResources {
    private final AliasTypeValuesWritePlatformService aliasTypeValuesWritePlatformService;
    private final AliasTypeValuesReadPlatformService aliasTypeValuesReadPlatformService;
    @PostMapping("/CreateAliasTypeValuesRequest")
    public Response saveAliasTypeValues(@RequestBody CreateAliasTypeValuesRequest createAliasTypeValuesRequest){
        Response response = this.aliasTypeValuesWritePlatformService.createAliasTypeValues(createAliasTypeValuesRequest);
        return response;
    }
    @GetMapping
    public List<AliasTypeValues> fetchAll(){ return this.aliasTypeValuesReadPlatformService.fetchAllAliasTypeValues();}

    @GetMapping("/{id}")
    public AliasTypeValues fetchAliasTypeValuesById(@PathVariable(name = "id")Integer id){
        return this.aliasTypeValuesReadPlatformService.fetchAliasTypeValuesById(id);
    }

    @PutMapping("/{id}")
    public Response updateAliasTypeValues(@PathVariable Integer id, @RequestBody UpdateAliasTypeValuesRequest updateAliasTypeValuesRequest){
        Response response = this.aliasTypeValuesWritePlatformService.updateAliasTypeValues(id,updateAliasTypeValuesRequest);
        return  response;
    }
}
