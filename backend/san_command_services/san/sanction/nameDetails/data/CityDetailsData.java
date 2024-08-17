package com.ponsun.san.sanction.nameDetails.data;

import lombok.Data;

@Data
public class CityDetailsData {
    private String Citi_country;
    private Integer Entity_logical_id_citi;


    public CityDetailsData (String Citi_country , Integer Entity_logical_id_citi ) {
        this.Citi_country = Citi_country;
        this.Entity_logical_id_citi = Entity_logical_id_citi;
    }

    public static CityDetailsData newInstance (String Citi_country , Integer Entity_logical_id_citi ) {
        return new CityDetailsData(Citi_country, Entity_logical_id_citi);
    }

}
