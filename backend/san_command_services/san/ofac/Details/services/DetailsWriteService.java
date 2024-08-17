package com.ponsun.san.ofac.Details.services;

import com.ponsun.san.ofac.Details.data.DetailsData;

import java.util.List;

public interface DetailsWriteService {
    List<DetailsData> fetchAllDetailsData(Integer id);
}
