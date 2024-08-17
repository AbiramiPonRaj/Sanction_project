package com.ponsun.san.aliases.aliasType.data;

import lombok.Data;

@Data
public class AliasTypeData {
    private String PrimaryKey;
    private String ID;
    private String Text;
    private String FK_AliasTypeValues;
    public AliasTypeData (final String PrimaryKey, final String ID, final String Text, final String FK_AliasTypeValues) {
        this.PrimaryKey =   PrimaryKey;
        this.ID = ID;
        this.Text = Text;
        this.FK_AliasTypeValues = FK_AliasTypeValues;
    }

    public static AliasTypeData newInstance (final String PrimaryKey, final String ID, final String Text, final String FK_AliasTypeValues) {
        return new AliasTypeData(PrimaryKey, ID, Text, FK_AliasTypeValues);
    }
}
