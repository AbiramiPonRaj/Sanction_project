


package com.ponsun.san.ofac.liststatus.services;

import com.ponsun.san.ofac.liststatus.data.ListStatusData;

import java.util.List;

public interface ListStatusWriteService {
    List<ListStatusData> fetchAlllistStatus(String id);
}
