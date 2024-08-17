package com.ponsun.san.searchLifcycle.search.data;

import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.searchLifcycle.search.request.CreateSearchRequest;
import com.ponsun.san.searchLifcycle.search.request.UpdateSearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SearchDataValidator {
    public void validateSaveSearchData(final CreateSearchRequest request) {
//        if (request.getName() == null || request.getName().equals("")) {
//            throw new EQAS_SAN_ApplicationException("Search Name parameter required");
//        }
//        if (request.getListId() == null || request.getListId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("Search ListId parameter required");
//        }

//        if (request.getTypeId() == null || request.getTypeId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("Search TypeId parameter required");
//        }
//        if (request.getMatchingScore() == null || request.getMatchingScore().equals("")) {
//            throw new EQAS_SAN_ApplicationException("Search MatchingScore parameter required");
//        }
//        if (request.getCountryId() == null || request.getCountryId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("Search CountryId parameter required");
//        }
//        if (request.getLevelId() == null || request.getLevelId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("Search LevelId parameter required");
//        }
//        if (request.getStatusId() == null || request.getStatusId().equals("")) {
//            throw new EQAS_SAN_ApplicationException("HitData StatusId parameter required");
//        }
    }

    public void validateUpdateSearchData(final UpdateSearchRequest request) {
        if (request.getName() == null || request.getName().equals("")) {
            throw new EQAS_SAN_ApplicationException("Search Name parameter required");
        }
        if (request.getListId() == null || request.getListId().equals("")) {
            throw new EQAS_SAN_ApplicationException("Search ListId parameter required");
        }

        if (request.getTypeId() == null || request.getTypeId().equals("")) {
            throw new EQAS_SAN_ApplicationException("Search TypeId parameter required");
        }
//        if (request.getMatchingScore() == null || request.getMatchingScore().equals("")) {
//            throw new EQAS_SAN_ApplicationException("Search MatchingScore parameter required");
//        }
        if (request.getCountryId() == null || request.getCountryId().equals("")) {
            throw new EQAS_SAN_ApplicationException("Search CountryId parameter required");
        }
        if (request.getLevelId() == null || request.getLevelId().equals("")) {
            throw new EQAS_SAN_ApplicationException("Search LevelId parameter required");
        }
    }
}
