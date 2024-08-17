package com.ponsun.san.adminconfiguration.AdminUser.services;

import com.ponsun.san.adminconfiguration.AdminUser.request.CreateUserRequest;
import com.ponsun.san.adminconfiguration.AdminUser.request.UpdateUserRequest;
import com.ponsun.san.infrastructure.utils.Response;
import org.springframework.transaction.annotation.Transactional;

public interface UserWritePlatformService {

    Response createUser(CreateUserRequest createUserRequest);


    @Transactional
    Response updateUser(Integer id, UpdateUserRequest updateUserRequest);



    @Transactional
    Response blockUser(Integer id);

    @Transactional
    Response unblockUser(Integer id);
}
