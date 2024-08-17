package com.ponsun.san.adminconfiguration.resetpassword.services;


import com.ponsun.san.adminconfiguration.resetpassword.domain.ResetPassword;

import java.util.List;

public interface ResetPasswordReadPlatformService {
    ResetPassword fetchResetPasswordById(Integer id);
    List<ResetPassword> fetchAllResetPassword();


}
