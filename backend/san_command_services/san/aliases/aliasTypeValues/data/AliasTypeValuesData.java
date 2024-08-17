package com.ponsun.san.aliases.aliasTypeValues.data;

import lombok.Data;

@Data
public class AliasTypeValuesData {

    private String PrimaryKey;
    private String FK_ReferenceValueSets;


    public AliasTypeValuesData(final String PrimaryKey, final  String FK_ReferenceValueSets) {
        this.PrimaryKey = PrimaryKey;
        this.FK_ReferenceValueSets = FK_ReferenceValueSets;
    }

    public static AliasTypeValuesData newInstance (final String PrimaryKey, final  String FK_ReferenceValueSets) {
        return new AliasTypeValuesData(PrimaryKey, FK_ReferenceValueSets);
    }
}
