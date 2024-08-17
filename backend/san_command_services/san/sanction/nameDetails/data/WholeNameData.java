package com.ponsun.san.sanction.nameDetails.data;

import lombok.Data;



@Data
public class WholeNameData {
    private Integer id;
    private String NAME;
    private String fileType;

    public WholeNameData (Integer id , String NAME,String fileType) {
        this.id = id ;
        this.NAME = NAME;
        this.fileType = fileType;
    }
    public static WholeNameData newInstance (Integer id , String NAME,String fileType) {
        return new WholeNameData(id, NAME,fileType);
    }

}
