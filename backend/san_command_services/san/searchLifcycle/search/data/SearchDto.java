package com.ponsun.san.searchLifcycle.search.data;

import com.ponsun.san.searchLifcycle.hitrecord.data.HitRecordData;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor

public class SearchDto {
    private Integer id;
    private String name;
    private Double matchingScore;
    private Integer listId;
    private Integer typeId;
    private Integer stateId;
    private Integer countryId;
    private String identity;
    private Long levelId;
    private Long uid;
    private Boolean valid;
    private Integer kycId;
    private List<HitRecordData> hitRecordData;

    public SearchDto( Integer id, String name, Double matchingScore, Integer listId, Integer typeId, Integer stateId, Integer countryId, String identity, Long levelId, Long uid, Boolean valid,Integer kycId,List<HitRecordData> hitRecordData) {

        this.id = id;
        this.name = name;
        this.matchingScore = matchingScore;
        this.listId = listId;
        this.typeId = typeId;
        this.stateId = stateId;
        this.countryId = countryId;
        this.identity = identity;
        this.levelId = levelId;
        this.uid = uid;
        this.valid = valid;
        this.kycId = kycId;
        this.hitRecordData = hitRecordData;
    }

    public static SearchDto newInstance( Integer id, String name, Double matchingScore, Integer listId, Integer typeId, Integer stateId, Integer countryId, String identity, Long levelId, Long uid, Boolean valid,Integer kycId,List<HitRecordData> hitRecordData){
        return new SearchDto(id,name,matchingScore,listId,typeId,stateId,countryId,identity,levelId,uid,valid,kycId,hitRecordData);
    }
}
