package com.ponsun.san.sanction.unDetails.data;

import lombok.Data;



@Data
public class UnAliasDetailsData {
    private String _Type;
    private String name;
    private String quality;

    public UnAliasDetailsData (String type , String name, String quality) {
        this._Type = type ;
        this.name = name;
        this.quality = quality;
    }
    public static UnAliasDetailsData newInstance (String _Type , String name, String quality) {
        return new UnAliasDetailsData(_Type, name,quality);
    }

}
