package com.ponsun.san.ofac.LookUpSearch.data;

import lombok.Data;

@Data
public class LookUpSearchData {
    private Integer ids;
    private String NAME;

    public LookUpSearchData(Integer ids, String NAME) {
        this.ids = ids;
        this.NAME = NAME;
    }
    public static LookUpSearchData newinsatnce(Integer ids, String NAME){
        return new LookUpSearchData(ids,NAME);
    }
}
