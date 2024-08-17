package com.ponsun.san.sanction.nameDetails.data;

import lombok.Data;

@Data
public class NameDetailsData {
    private String Title;
    private String Name_1;
    private String Gender;
    private String DOB;
    private String Town_of_Birth;

    public NameDetailsData (String Title , String Name_1 , String Gender , String DOB , String Town_of_Birth) {
        this.Title = Title;
        this.Name_1 = Name_1;
        this.Gender = Gender;
        this.DOB = DOB;
        this.Town_of_Birth = Town_of_Birth;
    }

    public static NameDetailsData newInstance (String Title , String Name_1 , String Gender , String DOB , String Town_of_Birth) {
        return new NameDetailsData(Title, Name_1, Gender, DOB, Town_of_Birth);
    }
}
