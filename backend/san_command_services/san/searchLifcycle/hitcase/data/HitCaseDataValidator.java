package com.ponsun.san.searchLifcycle.hitcase.data;

import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.searchLifcycle.hitcase.request.CreateHitCaseRequest;
import com.ponsun.san.searchLifcycle.hitcase.request.UpdateHitCaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HitCaseDataValidator {
    public void validateSaveHitCaseData(final CreateHitCaseRequest request) {
//        if (request.getDisplay() == null || request.getDisplay().equals("")) {
//            throw new CrmAppicationException("Display parameter required");
//        }
        if (request.getSearchId() == null || request.getSearchId().equals("")) {
            throw new EQAS_SAN_ApplicationException("SearchId parameter required");
        }
//        if (request.getHitId() == null || request.getHitId().equals("")) {
//            throw new CrmAppicationException("HitId parameter required");
//        }
//        if (request.getCriminalId() == null || request.getCriminalId().equals("")) {
//            throw new CrmAppicationException("CriminalId parameter required");
//        }
//        if (request.getLevelId() == null || request.getLevelId().equals("")) {
//            throw new CrmAppicationException("LevelId parameter required");
//        }
//        if (request.getStatusNowId() == null || request.getStatusNowId().equals("")) {
//            throw new CrmAppicationException("StatusNowId parameter required");
//        }
//        if (request.getCycleId() == null || request.getCycleId().equals("")) {
//            throw new CrmAppicationException("CycleId parameter required");
//        }
    }
    public void validateUpdateHitCaseData(final UpdateHitCaseRequest request) {
//        if (request.getDisplay() == null || request.getDisplay().equals("")) {
//            throw new CrmAppicationException("Display parameter required");
//        }
        if (request.getSearchId() == null || request.getSearchId().equals("")) {
            throw new EQAS_SAN_ApplicationException("SearchId parameter required");
        }
//        if (request.getHitId() == null || request.getHitId().equals("")) {
//            throw new CrmAppicationException("HitId parameter required");
//        }
//        if (request.getCriminalId() == null || request.getCriminalId().equals("")) {
//            throw new CrmAppicationException("CriminalId parameter required");
//        }
//        if (request.getLevelId() == null || request.getLevelId().equals("")) {
//            throw new CrmAppicationException("LevelId parameter required");
//        }
//        if (request.getStatusNowId() == null || request.getStatusNowId().equals("")) {
//            throw new CrmAppicationException("StatusNowId parameter required");
//        }
//        if (request.getCycleId() == null || request.getCycleId().equals("")) {
//            throw new CrmAppicationException("CycleId parameter required");
//        }
 }
}

