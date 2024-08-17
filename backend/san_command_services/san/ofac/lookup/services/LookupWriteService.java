package com.ponsun.san.ofac.lookup.services;

import com.ponsun.san.ofac.lookup.data.LookUpData;

import java.util.List;

public interface LookupWriteService {
    List<LookUpData> fetchAllLookUpData();
}
