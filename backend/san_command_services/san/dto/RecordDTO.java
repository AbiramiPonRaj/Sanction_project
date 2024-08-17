package com.ponsun.san.dto;
import com.ponsun.san.ofac.Count.data.SanctionDetailData;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class RecordDTO {

    private Integer Ids;
    private Integer searchId;
    private Integer criminalId;
    private Integer hitId;
    private String NAME;
    private String address;
    private String EntityType;
    private String program;
    private String LIST;
    private Double Score;
    private Integer fileType;
    private String fileList;
    private String nationality;
    private String citizenship;
    private String passport;

    public RecordDTO(Integer ids, Integer searchId,Integer criminalId,Integer hitId,String NAME, String address, String entityType, String program, String LIST, double score,Integer fileType,String fileList,String nationality,String citizenship,String passport) {
        this.Ids = ids;
        this.searchId = searchId;
        this.criminalId = criminalId;
        this.hitId = hitId;
        this.NAME = NAME;
        this.address = address;
        this.EntityType = entityType;
        this.program = program;
        this.LIST = LIST;
        this.Score = score;
        this.fileType=fileType;
        this.fileList=fileList;
        this.nationality=nationality;
        this.citizenship=citizenship;
        this.passport=passport;
    }


    public static RecordDTO newInstance(Integer ids, Integer searchId,Integer criminalId,Integer hitId, String NAME, String address, String entityType, String program, String LIST, double score,Integer fileType,String fileList,String nationality,String citizenship,String passport) {
        return new RecordDTO( ids,searchId,criminalId,hitId, NAME, address, entityType, program, LIST, score,fileType,fileList,nationality,citizenship,passport);
    }
}
