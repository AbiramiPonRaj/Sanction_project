package com.ponsun.san.master.status.data;

import lombok.Data;

@Data
public class StatusData {
    private Integer id;
    private String code;
    private String name;
    private Boolean valid;

    public StatusData(final Integer id, final String code, final String name, final Boolean valid) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.valid = valid;
    }

    public static StatusData newInstance(final Integer id, final String code, final String name, final Boolean valid) {
        return new StatusData(id, code, name, valid);
    }
}
