package com.ponsun.san.master.list.services;

import com.ponsun.san.master.list.domain.Lists;

import java.util.List;

public interface ListReadPlatformService {
    List<Lists> fetchAllList();

    Lists fetchListById(Integer id);
}
