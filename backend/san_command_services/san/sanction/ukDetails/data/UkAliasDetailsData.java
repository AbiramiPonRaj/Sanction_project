package com.ponsun.san.sanction.ukDetails.data;

import lombok.Data;

@Data
public class UkAliasDetailsData {
    private String name;
    private Integer Group_ID;
    private String Alias_Type;
    public UkAliasDetailsData (String name , Integer Group_ID , String Alias_Type ) {
        this.name = name;
        this.Group_ID = Group_ID;
        this.Alias_Type = Alias_Type;
    }
    public static UkAliasDetailsData newInstance (String name , Integer Group_ID , String Alias_Type ) {
        return new UkAliasDetailsData(name, Group_ID, Alias_Type);
    }
}
