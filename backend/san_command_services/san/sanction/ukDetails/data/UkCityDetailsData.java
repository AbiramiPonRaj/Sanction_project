package com.ponsun.san.sanction.ukDetails.data;

import lombok.Data;

@Data
public class UkCityDetailsData {
    private String NAME;
    private String dob;
    private String Place_of_Birth;
    private String Citizenship;
    private String Group_Type;
    private Integer Group_ID;

    public UkCityDetailsData(String NAME , String dob , String Place_of_Birth , String Citizenship , String Group_Type , Integer Group_ID) {
        this.NAME = NAME;
        this.dob = dob;
        this.Place_of_Birth = Place_of_Birth;
        this.Citizenship = Citizenship;
        this.Group_Type = Group_Type;
        this.Group_ID = Group_ID;
    }

    public static UkCityDetailsData newInstance (String NAME , String dob , String Place_of_Birth , String Citizenship , String Group_Type , Integer Group_ID) {
        return  new UkCityDetailsData(NAME, dob, Place_of_Birth, Citizenship, Group_Type, Group_ID);
    }
}
