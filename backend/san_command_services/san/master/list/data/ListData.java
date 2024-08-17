package com.ponsun.san.master.list.data;

import lombok.Data;

@Data
public class ListData {
    private String PrimaryKey;
    private String ID;
    private String Text;
    private String FK_ListValues;
    public ListData (final String PrimaryKey, final String ID, final String Text, final String FK_ListValues) {
        this.PrimaryKey =   PrimaryKey;
        this.ID = ID;
        this.Text = Text;
        this.FK_ListValues = FK_ListValues;
    }

    public static ListData newInstance (final String PrimaryKey, final String ID, final String Text, final String FK_ListValues) {
        return new ListData(PrimaryKey, ID, Text, FK_ListValues);
    }
}
