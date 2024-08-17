package com.ponsun.san.aliases.alias.data;

import lombok.Data;

@Data
public class AliasData {

    private String PrimaryKey;
    private String FixedRef;
    private String AliasTypeID;
    private String Primary;
    private String LowQuality;
    private String FK_Identity;

    public AliasData (final String PrimaryKey , final String FixedRef , final  String AliasTypeID ,final String Primary , final String LowQuality , final String FK_Identity) {
        this.PrimaryKey = PrimaryKey;
        this.FixedRef = FixedRef;
        this.AliasTypeID = AliasTypeID;
        this.Primary = Primary;
        this.LowQuality = LowQuality;
        this.FK_Identity = FK_Identity;
    }

    public static AliasData newInstance (final String PrimaryKey , final String FixedRef , final  String AliasTypeID ,final String Primary , final String LowQuality , final String FK_Identity) {
        return new AliasData(PrimaryKey,FixedRef,AliasTypeID,Primary,LowQuality,FK_Identity);
    }
}
