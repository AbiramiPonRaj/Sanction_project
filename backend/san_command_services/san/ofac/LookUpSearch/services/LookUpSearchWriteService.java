package com.ponsun.san.ofac.LookUpSearch.services;

import com.ponsun.san.ofac.LookUpSearch.data.LookUpSearchData;

import java.util.List;

public interface LookUpSearchWriteService {
    List<LookUpSearchData> fetchAllLookUpSearchData();

}
