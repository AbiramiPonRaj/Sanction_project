package com.ponsun.san.sanction.nameDetails.data;

import lombok.Data;

@Data
public class NationalIdentificationData {

    private String identity;
    private String number;
    private String det;
    private Integer Group_ID;

    public NationalIdentificationData(String identity, String number, String det, Integer group_ID) {
        this.identity = identity;
        this.number = number;
        this.det = det;
        Group_ID = group_ID;
    }
public static NationalIdentificationData newInstance (String identity, String number, String det, Integer group_ID) {
        return new NationalIdentificationData(identity,number,det,group_ID);
}

}
