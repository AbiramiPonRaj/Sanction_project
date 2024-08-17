package com.ponsun.san.sanction.nameDetails.data;

import lombok.Data;

@Data
public class AddressFileData {
    private String Addr_number;
    private String Addr_street;
    private String Addr_zipcode;
    private String Addr_city;
    private String Addr_country;
    private String Addr_other;
    private Integer Entity_logical_id_Addr;

    public AddressFileData(String Addr_number, String Addr_street, String Addr_zipcode, String Addr_city, String Addr_country, String Addr_other, Integer Entity_logical_id_Addr) {
        this.Addr_number = Addr_number;
        this.Addr_street = Addr_zipcode;
        this.Addr_zipcode = Addr_zipcode;
        this.Addr_city = Addr_city;
        this.Addr_country = Addr_country;
        this.Addr_other = Addr_other;
        this.Entity_logical_id_Addr = Entity_logical_id_Addr;
    }

    public static AddressFileData newInstance (String Addr_number, String Addr_street, String Addr_zipcode, String Addr_city, String Addr_country, String Addr_other, Integer Entity_logical_id_Addr) {
        return new AddressFileData(Addr_number, Addr_street, Addr_zipcode, Addr_city, Addr_country, Addr_other,Entity_logical_id_Addr);
    }
}
