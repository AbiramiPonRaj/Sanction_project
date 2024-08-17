package com.ponsun.san.adminconfiguration.admingroup.api;


import com.ponsun.san.adminconfiguration.admingroup.domain.Admingroup;
import com.ponsun.san.adminconfiguration.admingroup.request.CreateAdmingroupRequest;
import com.ponsun.san.adminconfiguration.admingroup.request.UpdateAdmingroupRequest;
import com.ponsun.san.adminconfiguration.admingroup.services.AdmingroupReadPlatformService;
import com.ponsun.san.adminconfiguration.admingroup.services.AdmingroupWritePlatformService;
import com.ponsun.san.infrastructure.utils.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/Admingroup")
@Tag(name = "AdmingroupApiResource")
public class AdmingroupApiResource {

    private final AdmingroupWritePlatformService admingroupWritePlatformService ;
    private final AdmingroupReadPlatformService admingroupReadPlatformService;

    @PostMapping("/CreateAdmingroupRequest")
    public Response saveAdmingroup(@RequestBody CreateAdmingroupRequest createAdmingroupRequest) {
        log.debug("START saveAdmingroup request body {}",createAdmingroupRequest);
        Response response = this.admingroupWritePlatformService.createAdmingroup(createAdmingroupRequest);
        log.debug("START saveAdmingroup response",response);
        return response;
    }

    @GetMapping
    public List<Admingroup> fetchAll() {
        return this.admingroupReadPlatformService.fetchAllAdmingroup();
    }

    @GetMapping("/{id}")
    public Admingroup fetchAdmingroupById(@PathVariable Integer id) {
        return this.admingroupReadPlatformService.fetchAdmingroupById(id);
    }

    @PutMapping("/{id}")
    public Response updateAdmingroup(@PathVariable Integer id, @RequestBody UpdateAdmingroupRequest updateAdmingroupRequest) {
        log.debug("START updateAdmingroup request body {}",updateAdmingroupRequest);
        Response response = this.admingroupWritePlatformService.updateAdmingroup(id, updateAdmingroupRequest);
        log.debug("START updateAdmingroup response",response);
        return response;
    }

    @PutMapping("/{id}/block")
    public Response blockAdmingroup(@PathVariable Integer id) {
        Response response = this.admingroupWritePlatformService.blockAdmingroup(id);
        return response;
    }

    @PutMapping("/{id}/unblock")
    public Response unblockAdmingroup(@PathVariable Integer id) {
        Response response = this.admingroupWritePlatformService.unblockAdmingroup(id);
        return response;
    }
}