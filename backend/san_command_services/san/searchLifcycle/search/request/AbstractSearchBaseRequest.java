package com.ponsun.san.searchLifcycle.search.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AbstractSearchBaseRequest {

    //private Integer id;
    private String name;
    private Double matching_score;
    private Integer listId;
    private Integer typeId;
    private Integer stateId;
    private Integer countryId;
    private String identity;
    private Long levelId;
    private LocalDateTime dt;
    private Long uid;
    private Boolean valid;
    private Integer kycId;

}