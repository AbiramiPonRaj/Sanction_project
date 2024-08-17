package com.ponsun.san.adminconfiguration.AdminUser.services;


import com.ponsun.san.adminconfiguration.AdminUser.domain.User;

import java.util.List;

public interface UserReadPlatformService {

    User fetchUserById(Integer id);
    List<User> fetchAllUsers();
}
