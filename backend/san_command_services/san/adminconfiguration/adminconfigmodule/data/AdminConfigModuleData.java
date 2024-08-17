package com.ponsun.san.adminconfiguration.adminconfigmodule.data;

import lombok.Data;

@Data
public class AdminConfigModuleData {
    private Integer id;
    private String name;
    private Boolean valid;
    private Integer orderno;
    private Integer uid;
    private Integer euid;


    public  AdminConfigModuleData (final Integer id,final String name,final Boolean valid,final Integer orderno,final Integer uid,final Integer euid){
        this.id = id;
        this.name = name;
        this.valid = valid;
        this.orderno = orderno;
        this.uid = uid;
        this.euid = euid;
    }

    public static AdminConfigModuleData newInstance (final Integer id,final String name,final Boolean valid,final Integer orderno,final Integer uid,final Integer euid) {
        return new AdminConfigModuleData(id,name,valid,orderno,uid,euid);
    }
}


