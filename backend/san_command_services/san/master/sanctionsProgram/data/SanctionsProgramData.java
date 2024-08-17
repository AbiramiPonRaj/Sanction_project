package com.ponsun.san.master.sanctionsProgram.data;

import lombok.Data;

@Data
public class SanctionsProgramData {

    private String PrimaryKey;
    private String ID;
    private String SubsidiaryBodyID;
    private String Text;
    private String FK_SanctionsProgramValues;

    public SanctionsProgramData (final String PrimaryKey, final String ID, final String SubsidiaryBodyID, final  String Text, final String FK_SanctionsProgramValues) {
        this.PrimaryKey = PrimaryKey;
        this.ID = ID;
        this.SubsidiaryBodyID = SubsidiaryBodyID;
        this.Text = Text;
        this.FK_SanctionsProgramValues = FK_SanctionsProgramValues;
    }

    public static SanctionsProgramData newInstance (final String PrimaryKey, final String ID, final String SubsidiaryBodyID, final  String Text, final String FK_SanctionsProgramValues) {
        return new SanctionsProgramData(PrimaryKey, ID, SubsidiaryBodyID, Text, FK_SanctionsProgramValues);
    }
}
