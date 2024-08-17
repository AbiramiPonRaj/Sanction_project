package com.ponsun.san.searchLifcycle.hitrecordtable.data;
import lombok.Data;


@Data
public class HitRecordDataTableData {

    private Integer searchId;
    private Integer criminalId;
    private Integer hitId;
    private String criminalName;
    private Double MatchScore;
    private String Country;
    private String State;
    private String dob;
    private String levelId;


    public HitRecordDataTableData(final Integer searchId, final Integer criminalId, final Integer hitId, final String criminalName, final Double MatchScore, final String Country, final String State, final String dob, final String levelId){
        this.searchId       = searchId;
        this.criminalId     = criminalId;
        this.hitId          = hitId;
        this.criminalName   = criminalName;
        this.MatchScore     = MatchScore;
        this.Country        = Country;
        this.State          = State;
        this.dob            = dob;
        this.levelId = levelId;
    }
    public  static HitRecordDataTableData newInstance(final Integer searchId, final Integer criminalId, final Integer hitId, final String criminalName, final Double MatchScore, final String Country, final String State, final String dob, final String levelId){
        return new HitRecordDataTableData(searchId,criminalId,hitId,criminalName,MatchScore,Country,State,dob,levelId);
    }
}
