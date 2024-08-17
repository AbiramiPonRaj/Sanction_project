package com.ponsun.san.sanction.nameDetails.data;

import lombok.Data;

@Data
public class AddressDetailsData {
    private String Address_1;
    private String Address_2;
    private String Address_3;
    private String Address_4;
    private String Address_5;
    private String Address_6;
    private String Post_ZipCode;
    private String Country;
    private String Other_Information;
    public AddressDetailsData(String Address_1, String Address_2, String Address_3,
                              String Address_4, String Address_5, String Address_6,
                              String Post_ZipCode, String Country, String Other_Information) {
        this.Address_1 = Address_1;
        this.Address_2 = Address_2;
        this.Address_3 = Address_3;
        this.Address_4 = Address_4;
        this.Address_5 = Address_5;
        this.Address_6 = Address_6;
        this.Post_ZipCode = Post_ZipCode;
        this.Country = Country;
        this.Other_Information = Other_Information;
    }

    public static AddressDetailsData newInstance(String Address_1, String Address_2, String Address_3,
                                                 String Address_4, String Address_5, String Address_6,
                                                 String Post_ZipCode, String Country, String Other_Information) {
        return new AddressDetailsData(Address_1, Address_2, Address_3, Address_4,
                Address_5, Address_6, Post_ZipCode, Country, Other_Information);
    }
}
