package com.ponsun.san.adminconfiguration.admingroup.data;

import lombok.Data;

@Data
public class AdmingroupData {
    private Integer id;
    private String moduleUrl;
    private String name;
    private Boolean valid;
    private Integer uid;
    private Integer euid;

    public AdmingroupData(final Integer id,final String moduleUrl,final String name,final Boolean valid,final Integer uid ,final  Integer euid) {
        this.id = id;
        this.moduleUrl = moduleUrl;
        this.name = name;
        this.valid = valid;
        this.uid = uid;
        this.euid = euid;

    }

    public static AdmingroupData newInstance(final Integer id,final String moduleUrl,final String name,final Boolean valid,final Integer uid,final Integer euid) {
        return new AdmingroupData(id,moduleUrl,name,valid,uid,euid);
    }

}
