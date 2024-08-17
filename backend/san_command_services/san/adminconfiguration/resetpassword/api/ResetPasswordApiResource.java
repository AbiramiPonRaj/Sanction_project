package com.ponsun.san.adminconfiguration.resetpassword.api;


import com.ponsun.san.adminconfiguration.resetpassword.domain.ResetPassword;
import com.ponsun.san.adminconfiguration.resetpassword.request.CreateResetPasswordRequest;
import com.ponsun.san.adminconfiguration.resetpassword.request.UpdateResetPasswordRequest;
import com.ponsun.san.adminconfiguration.resetpassword.services.ResetPasswordReadPlatformService;
import com.ponsun.san.adminconfiguration.resetpassword.services.ResetPasswordWritePlatformService;
import com.ponsun.san.infrastructure.utils.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/resetpassword")
@Tag(name = "ResetPasswordApiResource")
public class ResetPasswordApiResource {

    private final ResetPasswordWritePlatformService resetpasswordWritePlatformService;
    private final ResetPasswordReadPlatformService resetpasswordReadPlatformService;

    @PostMapping("/CreateResetPassword")
    public Response saveResetPassword(@RequestBody CreateResetPasswordRequest createResetPasswordRequest) {
        log.debug("START saveResetPassword request body {}",createResetPasswordRequest);
        Response response = this.resetpasswordWritePlatformService.createResetPassword(createResetPasswordRequest);
        log.debug("START saveResetPassword response",response);
        return response;
    }

    @GetMapping
    public List<ResetPassword> fetchAll() {
        return this.resetpasswordReadPlatformService.fetchAllResetPassword();
    }

    @GetMapping("/{id}")
    public ResetPassword fetchResetPasswordById(@PathVariable(name = "id") Integer id) {
        return this.resetpasswordReadPlatformService.fetchResetPasswordById(id);
    }
    @PutMapping("/{id}")
    public Response updateResetPassword(@PathVariable Integer id, @RequestBody UpdateResetPasswordRequest updateResetPasswordRequest) {
        log.debug("START updateResetPassword request body {}",updateResetPasswordRequest);
        Response response = this.resetpasswordWritePlatformService.updateResetPassword(id, updateResetPasswordRequest);
        log.debug("START updateResetPassword response",response);
        return response;
    }

    @PutMapping("/{id}/block")
    public Response blockResetPassword(@PathVariable Integer id) {
        Response response = this.resetpasswordWritePlatformService.blockResetPassword(id);
        return response;
    }

    @PutMapping("/{id}/unblock")
    public Response unblockResetPassword(@PathVariable Integer id) {
        Response response = this.resetpasswordWritePlatformService.unblockResetPassword(id);
        return response;
    }

}




