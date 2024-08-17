package com.ponsun.san.master.country.data;

import lombok.Data;

@Data
public class CountryData {
    private String PrimaryKey;
    private String ID;
    private String ISO2;
    private String Text;
    private String FK_CountryValues;
    public CountryData (final String PrimaryKey, final String ID, final String ISO2, final String Text, final String FK_CountryValues) {
        this.PrimaryKey =   PrimaryKey;
        this.ID = ID;
        this.ISO2 = ISO2;
        this.Text = Text;
        this.FK_CountryValues = FK_CountryValues;
    }

    public static CountryData newInstance (final String PrimaryKey, final String ID, final String ISO2, final String Text, final String FK_CountryValues) {
            return new CountryData(PrimaryKey, ID, ISO2, Text, FK_CountryValues);
    }
}
