package com.ponsun.san.uiTest.dto;

import lombok.Data;

@Data

public class UiReciveRecord {
    private double onesidematching;
    private double twosidematching;
    private double Jaro;
    private double tokenWeight;
    private double fuzzydouble_Metaphone_cosine;
    //private double sqlJaro;
//    private double newJaro;

    public UiReciveRecord(double onesidematching, double twosidematching,double Jaro, double tokenWeight) {
        this.onesidematching = onesidematching;
        this.twosidematching = twosidematching;

        this.Jaro   = Jaro;
        this.tokenWeight = tokenWeight;

    }
    public static UiReciveRecord newInstance(double onesidematching, double twosidematching,double Jaro,double tokenWeight) {
        return new UiReciveRecord(onesidematching,twosidematching,Jaro,tokenWeight);
    }

    public UiReciveRecord(){}
}
