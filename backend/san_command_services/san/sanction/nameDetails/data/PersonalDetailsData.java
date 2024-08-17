package com.ponsun.san.sanction.nameDetails.data;

import lombok.Data;

@Data
public class PersonalDetailsData {
    private String Naal_firstname;
    private String Naal_middlename;
    private String Naal_lastname;
    private String Naal_wholename;
    private Integer Entity_logical_id;

    public PersonalDetailsData(String Naal_firstname, String Naal_middlename, String Naal_lastname, String Naal_wholename, Integer Entity_logical_id) {
        this.Naal_firstname = Naal_firstname;
        this.Naal_middlename = Naal_middlename;
        this.Naal_lastname = Naal_lastname;
        this.Naal_wholename = Naal_wholename;
        this.Entity_logical_id = Entity_logical_id;
    }

    public static PersonalDetailsData newInstance(String Naal_firstname, String Naal_middlename, String Naal_lastname, String Naal_wholename, Integer Entity_logical_id) {
        return new PersonalDetailsData(Naal_firstname, Naal_middlename, Naal_lastname,Naal_wholename, Entity_logical_id);
    }
}
