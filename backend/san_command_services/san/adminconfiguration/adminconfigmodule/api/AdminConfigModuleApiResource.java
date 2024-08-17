package com.ponsun.san.adminconfiguration.adminconfigmodule.api;



import com.ponsun.san.adminconfiguration.adminconfigmodule.domain.AdminConfigModule;
import com.ponsun.san.adminconfiguration.adminconfigmodule.request.CreateAdminConfigModuleRequest;
import com.ponsun.san.adminconfiguration.adminconfigmodule.request.UpdateAdminConfigModuleRequest;
import com.ponsun.san.adminconfiguration.adminconfigmodule.services.AdminConfigModuleReadPlatformServiceImpl;
import com.ponsun.san.adminconfiguration.adminconfigmodule.services.AdminConfigModuleWritePlatformServiceImpl;
import com.ponsun.san.infrastructure.utils.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/adminModule")
@Tag(name="AdminConfigModuleApiResource")
public class AdminConfigModuleApiResource {

    private final AdminConfigModuleWritePlatformServiceImpl adminConfigModuleWritePlatformService;
    private final AdminConfigModuleReadPlatformServiceImpl adminConfigModuleReadPlatformService;

    @PostMapping("/createAdminModule")
    public Response saveAdminConfigModule(@RequestBody CreateAdminConfigModuleRequest createAdminConfigModuleRequest) {
        log.debug("START saveAdminConfigModule request body {}",createAdminConfigModuleRequest);
        Response response = this.adminConfigModuleWritePlatformService.createAdminConfigModule(createAdminConfigModuleRequest);
        log.debug("START saveAdminConfigModule response",response);
        return response;
    }
    @GetMapping
    public List<AdminConfigModule> fetchAll(){
        return this.adminConfigModuleReadPlatformService.fetchAllAdminConfigModule();
    }

    @GetMapping("/{id}")
    public AdminConfigModule fetchAdminConfigModuleById (@PathVariable  Integer id) {
        return this.adminConfigModuleReadPlatformService.fetchAdminConfigModuleById(id);
    }
    @PutMapping("/{id}")
    public Response updateAdminConfigModule(@PathVariable Integer id, @RequestBody UpdateAdminConfigModuleRequest updateAdminConfigModuleRequest) {
        log.debug("START updateAdminConfigModule request body {}",updateAdminConfigModuleRequest);
        Response response = this.adminConfigModuleWritePlatformService.updateAdminConfigModule(id, updateAdminConfigModuleRequest);
        log.debug("START updateAdminConfigModule response",response);
        return response;
    }


    @PutMapping("/deactive/{id}")
    public Response deactive(@PathVariable Integer id, Integer euid) {
        Response response = this.adminConfigModuleWritePlatformService.deactive(id, euid);
        return response;
    }


    @PutMapping("/block/{id}")
    public Response blockAdminConfigModule(@PathVariable Integer id){
        Response response = this.adminConfigModuleWritePlatformService.blockAdminConfigModule(id);
        return response;
}

@PutMapping("/unblock/{id}")
    public Response unblockAdminConfigModule(@PathVariable Integer id){
        Response response = this.adminConfigModuleWritePlatformService.unblockAdminConfigModule(id);
        return response;
}

}

