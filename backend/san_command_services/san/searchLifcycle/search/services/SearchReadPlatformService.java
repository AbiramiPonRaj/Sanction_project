package com.ponsun.san.searchLifcycle.search.services;


//import com.ponsun.san.SearchDetail.data.SearchDetailData;
import com.ponsun.san.Record.RecordDtos;
import com.ponsun.san.searchLifcycle.hitrecord.data.HitRecordData;
import com.ponsun.san.searchLifcycle.search.data.SearchData;
import com.ponsun.san.searchLifcycle.search.domain.Search;

import java.util.List;

public interface SearchReadPlatformService {
    Search fetchSearchById(Integer id);
    List<Search> fetchAllSearch();
    List<SearchData> fetchAllSearchDetailData(String fromDate, String toDate);
    List<HitRecordData>fetchAllRecordData(String fromDate, String toDate);
    List<RecordDtos> fetchAllDetailData(String fromDate, String toDate);


}
