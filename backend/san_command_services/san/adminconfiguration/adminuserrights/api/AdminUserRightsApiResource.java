package com.ponsun.san.adminconfiguration.adminuserrights.api;

import com.ponsun.san.adminconfiguration.adminuserrights.data.AdminUserRightsDTO;
import com.ponsun.san.adminconfiguration.adminuserrights.domain.AdminUserRights;
import com.ponsun.san.adminconfiguration.adminuserrights.request.UpdateAdminUserRightsRequest;
import com.ponsun.san.adminconfiguration.adminuserrights.services.AdminUserRightsReadPlatformService;
import com.ponsun.san.adminconfiguration.adminuserrights.services.AdminUserRightsWritePlatformService;
import com.ponsun.san.infrastructure.utils.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/adminUserRighters")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "AdminUserRightsApiResource")
public class AdminUserRightsApiResource {
    private final AdminUserRightsWritePlatformService adminUserRightsWritePlatformService;
    private final AdminUserRightsReadPlatformService adminUserRightsReadPlatformService;
//    public ResponseEntity<String> saveAdminUserRights(@RequestBody List<AdminUserRightsDTO> createAdminUserRightsDtoRequests) {
//        createAdminUserRightsDtoRequests.forEach(request -> {
//            System.out.println(request.toString());
//        });
//
//        return ResponseEntity.ok("lehpbfmpoElements printed successfully.");
//    }

    @PostMapping("/createAdminUserRights")
    public ResponseEntity<String> saveAdminUserRights(@RequestBody List<AdminUserRightsDTO> createAdminUserRightsDtoRequests) {
        try {
            for (AdminUserRightsDTO dto : createAdminUserRightsDtoRequests) {
                // Create an instance of AdminUserRights from the DTO
                AdminUserRights adminUserRights = AdminUserRights.Bulkcreate(dto);

                // Call the service to save the entity, one at a time
                adminUserRightsWritePlatformService.createAdminUserRights(Collections.singletonList(dto));

                // Optionally, log the saved entity
                log.info("Saved AdminUserRights with ID: " + adminUserRights.getUid());
            }

            // Return a success response
            return ResponseEntity.ok("AdminUserRights data saved successfully.");
        } catch (Exception e) {
            // Handle exceptions and return an error response
            log.error("Error saving AdminUserRights data: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving AdminUserRights data.");
        }
    }

    @GetMapping
    public List<AdminUserRights> fetchAll() {
        return this.adminUserRightsReadPlatformService.fetchAllAdminUserRights();
    }

    @GetMapping("/{id}")
    public AdminUserRights fetchAdminUserRightsById(@PathVariable Integer id) {
        return this.adminUserRightsReadPlatformService.fetchAdminUserRightsById(id);
    }
    @PutMapping("/{id}")
    public Response updateAdminUserRights(@PathVariable Integer id, @RequestBody UpdateAdminUserRightsRequest updateAdminUserRightsRequest) {
        log.debug("START updateAdminUserRights request body {}",updateAdminUserRightsRequest);
        Response response = this.adminUserRightsWritePlatformService.updateAdminUserRights(id, updateAdminUserRightsRequest);
        log.debug("START updateAdminUserRights response",response);
        return response;
    }

    @PutMapping("/{id}/block")
    public Response blockAdminUserRights(@PathVariable Integer id) {
        Response response = this.adminUserRightsWritePlatformService.blockAdminUserRights(id);
        return response;
    }
    @PutMapping("/{id}/unblock")
    public Response unblockAdminUserRights(@PathVariable Integer id) {
        Response response = this.adminUserRightsWritePlatformService.unblockAdminUserRights(id);
        return response;
    }

}
