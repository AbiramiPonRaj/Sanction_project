package com.ponsun.san.sanction.unDetails.data;

import lombok.Data;

@Data
public class UnDesignationDetailsData {
    private String  identity;;
    public UnDesignationDetailsData(String identity) {
        this.identity = identity;
    }

    public static UnDesignationDetailsData newInstance(String identity) {
        return new UnDesignationDetailsData(identity);
    }
}
