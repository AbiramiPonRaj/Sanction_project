package com.ponsun.san.ofac.LookUpResults.services;

import com.ponsun.san.ofac.LookUpResults.data.LookUpResultsData;
import java.util.List;

public interface LookUpResultsWriteService {
    List<LookUpResultsData> fetchAllLookUpResultsData(String name);
}
