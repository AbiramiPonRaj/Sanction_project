package com.ponsun.san.ofac.Details.data;

import lombok.Data;

@Data
public class DetailsData {
    private String heading;
    private String val;

    public DetailsData(String heading, String val) {
        this.heading = heading;
        this.val = val;
    }
    public static DetailsData newInstance(String heading, String val){

        return new DetailsData(heading,val);
    }
}
