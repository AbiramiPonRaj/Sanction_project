package com.ponsun.san.master.list.services;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.master.list.request.CreateListRequest;
import com.ponsun.san.master.list.request.UpdateListRequest;

public interface ListWritePlatformService {
    Response createList(CreateListRequest createListRequest);

    Response updateList(Integer id, UpdateListRequest updateListRequest);
}
