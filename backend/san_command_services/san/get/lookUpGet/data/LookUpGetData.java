package com.ponsun.san.get.lookUpGet.data;

import lombok.Data;

@Data
public class LookUpGetData {
    private String Name;
    private String Address;
    private String Type;
    private String Program;
    private String List;
    private String Score;

    public LookUpGetData (final String Name , final String Address , final String Type , final String Program , final  String List , final String Score ) {
        this.Name = Name;
        this.Address = Address;
        this.Type = Type;
        this.Program = Program;
        this.List = List;
        this.Score = Score;
    }

    public static LookUpGetData newInstance (final String Name , final String Address , final String Type , final String Program , final  String List , final String Score ) {
        return new LookUpGetData(Name, Address, Type, Program, List, Score);
    }
}
