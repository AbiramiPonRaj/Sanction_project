package com.ponsun.san.searchLifcycle.hitrecord.data;

import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.searchLifcycle.hitrecord.request.CreateHitRecordRequest;
import com.ponsun.san.searchLifcycle.hitrecord.request.UpdateHitRecordRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HitRecordValidator {
    public void validateSaveHitDataData(final CreateHitRecordRequest request) {
        if (request.getSearchId() == null || request.getSearchId().equals("")) {
            throw new EQAS_SAN_ApplicationException("HitData SearchId parameter required");
        }
//        if (request.getDisplay() == null || request.getDisplay().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData Display parameter required");
//        }
//
//        if (request.getCriminalId() == null || request.getCriminalId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData CriminalId parameter required");
//        }
//        if (request.getMatchingScore() == null || request.getMatchingScore().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData MatchingScore parameter required");
//        }
//        if (request.getName() == null || request.getName().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData Name parameter required");
//        }
//        if (request.getCycleId() == null || request.getCycleId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData CycleId parameter required");
//        }
//        if (request.getStatusId() == null || request.getStatusId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData StatusId parameter required");
//        }
    }

    public void validateUpdateHitDataData(final UpdateHitRecordRequest request) {
//        if (request.getSearchId() == null || request.getSearchId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData SearchId parameter required");
//        }
//        if (request.getDisplay() == null || request.getDisplay().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData Display parameter required");
//        }
//
//        if (request.getCriminalId() == null || request.getCriminalId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData CriminalId parameter required");
//        }
//        if (request.getMatchingScore() == null || request.getMatchingScore().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData MatchingScore parameter required");
//        }
//        if (request.getName() == null || request.getName().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData Name parameter required");
//        }
//        if (request.getCycleId() == null || request.getCycleId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData CycleId parameter required");
//        }
//        if (request.getStatusId() == null || request.getStatusId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData StatusId parameter required");
//        }
    }
}


