package com.ponsun.san.master.country.request;

import lombok.Data;

@Data
public class AbstractCountryRequest {

    private String PrimaryKey;
    private String ID;
    private String ISO2;
    private String Text;
    private String FK_CountryValues;
}
