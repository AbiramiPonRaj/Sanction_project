package com.ponsun.san.uiTest.dto;

import lombok.Data;

@Data
public class UiSearchDtoVerify {
    private String firstName;
    private String secondName;

    public UiSearchDtoVerify(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }
    public static UiSearchDtoVerify newInstance(String firstName, String secondName){
        return new UiSearchDtoVerify(firstName, secondName);
    }
}
