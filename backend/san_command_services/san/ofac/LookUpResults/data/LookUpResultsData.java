package com.ponsun.san.ofac.LookUpResults.data;

import lombok.Data;

@Data
public class LookUpResultsData {
    private Integer Ids;
    private String NAME;
    private String address;
    private String EntityType;
    private String program;
    private String LIST;
    private Integer Score;

    public LookUpResultsData(Integer Ids, String NAME, String address, String type, String program, String LIST, Integer Score) {
        this.Ids = Ids;
        this.NAME = NAME;
        this.address = address;
        this.EntityType = EntityType;
        this.program = program;
        this.LIST = LIST;
        this.Score = Score;
    }
    public static LookUpResultsData newInstance(Integer Ids, String NAME, String address, String type, String program, String LIST, Integer Score){
        return new LookUpResultsData(Ids,NAME,address,type,program,LIST,Score);
    }
}
