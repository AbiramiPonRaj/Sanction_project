package com.ponsun.san.FirstlevelPending.service;

import com.ponsun.san.FirstlevelPending.data.FirstlevelPendingData;
import java.util.List;

public interface FirstlevelPendingReadService {
    List<FirstlevelPendingData> fetchAllPendingData(Integer id);

}
