package com.ponsun.san.ofac.Count.service;
import com.ponsun.san.dto.RecordDTO;
import com.ponsun.san.dto.SearchDTO;
import org.apache.poi.ss.formula.functions.Count;

import java.util.List;

public interface CountReadService {
    List<Count> fetchAllCount(Integer id);
    List<RecordDTO>  getRecordsCount(SearchDTO searchDTO);

}
