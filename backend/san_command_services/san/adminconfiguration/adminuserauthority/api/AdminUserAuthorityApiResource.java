package com.ponsun.san.adminconfiguration.adminuserauthority.api;


import com.ponsun.san.adminconfiguration.adminuserauthority.domain.AdminUserAuthority;
import com.ponsun.san.adminconfiguration.adminuserauthority.request.CreateAdminUserAuthorityRequest;
import com.ponsun.san.adminconfiguration.adminuserauthority.request.UpdateAdminUserAuthorityRequest;
import com.ponsun.san.adminconfiguration.adminuserauthority.service.AdminUserAuthorityReadPlatformService;
import com.ponsun.san.adminconfiguration.adminuserauthority.service.AdminUserAuthorityWritePlatformService;
import com.ponsun.san.infrastructure.utils.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/adminUserAuthority")
@Tag(name = "AdminUserAuthorityApiResource")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminUserAuthorityApiResource {
        private final AdminUserAuthorityWritePlatformService adminUserAuthorityWritePlatformService;
        private final AdminUserAuthorityReadPlatformService adminUserAuthorityReadPlatformService;

    @PostMapping("/createAdminUserAuthority")
    public Response saveAdminUserAuthority(@RequestBody CreateAdminUserAuthorityRequest createAdminUserAuthorityRequest) {
        log.debug("START saveAdminUserAuthority request body {}",createAdminUserAuthorityRequest);
        Response response = this.adminUserAuthorityWritePlatformService.createAdminUserAuthority(createAdminUserAuthorityRequest);
        log.debug("START saveAdminUserAuthority response",response);
        return response;
    }
    @GetMapping
    public List<AdminUserAuthority> fetchAll() {
        return this.adminUserAuthorityReadPlatformService.fetchAllAdminUserAuthority();
    }

    @GetMapping("/{id}")
    public AdminUserAuthority fetchAdminUserAuthorityById(@PathVariable Integer id) {
        return this.adminUserAuthorityReadPlatformService.fetchAdminUserAuthorityById(id);
    }
    @PutMapping("/{id}")
    public Response updateAdminUserAuthority(@PathVariable Integer id, @RequestBody UpdateAdminUserAuthorityRequest updateAdminUserAuthorityRequest) {
        log.debug("START updateAdminUserAuthority request body {}",updateAdminUserAuthorityRequest);
        Response response = this.adminUserAuthorityWritePlatformService.updateAdminUserAuthority(id, updateAdminUserAuthorityRequest);
        log.debug("START updateAdminUserAuthority response",response);
        return response;
    }
   @PutMapping("/{id}/block")
    public Response blockAdminUserAuthority(@PathVariable Integer id){
        Response response = this.adminUserAuthorityWritePlatformService.blockAdminUserAuthority(id);
        return response;
}
    @PutMapping("/{id}/unblock")
    public Response unblockAdminUserAuthority(@PathVariable Integer id){
        Response response = this.adminUserAuthorityWritePlatformService.unblockAdminUserAuthority(id);
        return response;
    }
}
