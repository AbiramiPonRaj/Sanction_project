package com.ponsun.san.accessPermission.api;

import com.ponsun.san.accessPermission.data.AccessPermissionData;
import com.ponsun.san.accessPermission.services.AccessPermissionWritePlatformService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/accessPermission")
public class AccessPermissionApiResource {
    private final AccessPermissionWritePlatformService accessPermissionWritePlatformService;

    public AccessPermissionApiResource(AccessPermissionWritePlatformService accessPermissionWritePlatformService){
        this.accessPermissionWritePlatformService = accessPermissionWritePlatformService;
    }
    @GetMapping
    public List<AccessPermissionData> fetchAll(@RequestParam String uid){
        return this.accessPermissionWritePlatformService.fetchAllAccessPermissionData(uid);
    }
}
