package com.ponsun.san.EhcachePOC.Service;

import com.ponsun.san.EhcachePOC.Data.RecordDetailData;

import java.util.List;

public interface OFACDetailService {
    List<RecordDetailData> fetchTestingData();
    List<RecordDetailData> fetchTestingDataById(Integer id);

}
