package com.ponsun.san.master.all.data;

import lombok.Data;

@Data
public class AllData {
    private String partyTypeID ;
    private String partySubTypeID;
    private String partyText;
    private String partySubText;
    private String type_text;


    public AllData (final String partyTypeID, final String partySubTypeID, final String partyText, final String partySubText, final String type_text) {
        this.partyTypeID = partyTypeID;
        this.partySubTypeID = partySubTypeID;
        this.partyText = partyText;
        this.partySubText = partySubText;
        this.type_text = type_text;
    }

    public static AllData newInstance (final String partyTypeID, final String partySubTypeID, final String partyText, final String partySubText, final String type_text) {
       return new AllData(partyTypeID, partySubTypeID, partyText, partySubText, type_text);
    }

}
