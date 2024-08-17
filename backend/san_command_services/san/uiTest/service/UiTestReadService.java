package com.ponsun.san.uiTest.service;
import com.ponsun.san.dto.RecordDTO;
import com.ponsun.san.uiTest.dto.UiReciveRecord;
import com.ponsun.san.uiTest.dto.UiRecordDTO;
import com.ponsun.san.uiTest.dto.UiSearchDTO;
import com.ponsun.san.uiTest.dto.UiSearchDtoVerify;

import java.util.List;

public interface UiTestReadService {
    List<UiRecordDTO>  getuiTestRecords(UiSearchDTO searchDTO);
    List<UiReciveRecord>  getUiRecords(UiSearchDtoVerify uiSearchDtoVerify);

    String calculateJaroWinklerSimilarity(String str1, String str2);
}
