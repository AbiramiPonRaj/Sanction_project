package com.ponsun.san.sanction.nameDetails.data;

import lombok.Data;

@Data
public class BirthDetailsData {
    private Integer Entity_logical_id_birth;
    private String Birt_date;
    private String Birt_plcae;
    private String Birt_country;

    public BirthDetailsData (Integer Entity_logical_id_birth , String Birt_date , String Birt_plcae ,String Birt_country ) {
        this.Entity_logical_id_birth = Entity_logical_id_birth;
        this.Birt_date =Birt_date;
        this.Birt_plcae=Birt_plcae;
        this.Birt_country=Birt_country;
    }
public static BirthDetailsData newInstance (Integer Entity_logical_id_birth , String Birt_date , String Birt_plcae ,String Birt_country ) {
        return new BirthDetailsData(Entity_logical_id_birth, Birt_date, Birt_plcae, Birt_country);
}
}
