package com.ponsun.san.ofac.Count.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SanctionDetailData {
    private Integer ids;
    private String Group_Type;
    private String Nationality_country;
    private String Citi_country;
    private String Birt_country;
    private String Iden_country;
    private String Addr_country;

    public SanctionDetailData(Integer ids,String Group_Type, String Nationality_country, String Citi_country, String Birt_country, String Iden_country, String Addr_country) {
        this.ids=ids;
        this.Group_Type = Group_Type;
        this.Nationality_country = Nationality_country;
        this.Citi_country = Citi_country;
        this.Birt_country = Birt_country;
        this.Iden_country = Iden_country;
        this.Addr_country = Addr_country;
    }
    public static SanctionDetailData newInstance(Integer ids,String Group_Type, String Nationality_country, String Citi_country, String Birt_country, String Iden_country, String Addr_country){
        return new SanctionDetailData(ids,Group_Type,Nationality_country,Citi_country,Birt_country,Iden_country,Addr_country);
    }
}
