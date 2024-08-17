package com.ponsun.san.sanction.nameDetails.data;

import lombok.Data;

@Data
public class AliasesDetailsData {
    private String Alias_Type;
    private String Alias_Quality;
    private String NAME;
    private Integer Group_ID;

    public AliasesDetailsData (String Alias_Type, String Alias_Quality,String NAME , Integer Group_ID) {
        this.Alias_Type = Alias_Type;
        this.Alias_Quality = Alias_Quality;
        this.NAME = NAME;
        this.Group_ID= Group_ID;
    }

    public static AliasesDetailsData newInstance (String Alias_Type, String Alias_Quality,String NAME,Integer Group_ID) {
        return new AliasesDetailsData(Alias_Type , Alias_Quality , NAME,Group_ID);
    }
}
