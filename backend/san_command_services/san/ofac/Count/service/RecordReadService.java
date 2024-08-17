package com.ponsun.san.ofac.Count.service;

import com.ponsun.san.dto.RecordDTO;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecord;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface RecordReadService {
    void updateRecordDTO(RecordDetails recordDetails, Integer id, RecordDTO target) throws ExecutionException, InterruptedException;
   // List<RecordDTO> updateRecordDTO(RecordDetails recordDetails, List<HitRecord> source, List<RecordDTO> target) throws ExecutionException, InterruptedException;


}
