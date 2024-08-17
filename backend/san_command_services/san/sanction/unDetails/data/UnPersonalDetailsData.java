package com.ponsun.san.sanction.unDetails.data;

import lombok.Data;

@Data
public class UnPersonalDetailsData {
    private String _list;
    private String title;
    private String firstName;
    private String secName;
    private String ThirdName;
    private String GENDER;
    private String BirthType;
    private String dob;
    private String BirthPlace;
    private String Nationality;
    private String Citizenship;
    private String Remarks;
    private Integer DATAID;

    public UnPersonalDetailsData (String _list, String title, String firstName, String secName , String ThirdName,
                                 String GENDER,String BirthType,String dob,String BirthPlace,String Nationality,String Citizenship,String Remarks,Integer DATAID) {
        this._list = _list;
        this.title = title;
        this.firstName = firstName;
        this.secName = secName;
        this.ThirdName = ThirdName;
        this.GENDER = GENDER;
        this.BirthType = BirthType;
        this.dob = dob;
        this.BirthPlace = BirthPlace;
        this.Nationality = Nationality;
        this.Citizenship = Citizenship;
        this.Remarks = Remarks;
        this.DATAID = DATAID;
    }

    public static UnPersonalDetailsData newInstance (String _list, String title, String firstName, String secName , String ThirdName,
                                                     String GENDER,String BirthType,String dob,String BirthPlace,String Nationality,String Citizenship,String Remarks,Integer DATAID) {
        return new UnPersonalDetailsData(_list, title, firstName,secName, ThirdName,GENDER,BirthType,dob,BirthPlace,Nationality,Citizenship,Remarks,DATAID);
    }
}
