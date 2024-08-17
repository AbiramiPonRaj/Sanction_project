package com.ponsun.san.adminconfiguration.resetpassword.services;


import com.ponsun.san.adminconfiguration.resetpassword.request.CreateResetPasswordRequest;
import com.ponsun.san.adminconfiguration.resetpassword.request.UpdateResetPasswordRequest;
import com.ponsun.san.infrastructure.utils.Response;

public interface ResetPasswordWritePlatformService {
     Response createResetPassword(CreateResetPasswordRequest createResetPasswordRequest);
     Response updateResetPassword(Integer id, UpdateResetPasswordRequest updateResetPasswordRequest);
    Response blockResetPassword(Integer id);
    Response unblockResetPassword(Integer id);
}
