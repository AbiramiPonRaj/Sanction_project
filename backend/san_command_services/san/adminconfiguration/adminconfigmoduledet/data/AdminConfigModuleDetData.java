package com.ponsun.san.adminconfiguration.adminconfigmoduledet.data;

import lombok.Data;

@Data
public class AdminConfigModuleDetData {
    private Integer id;
    private Integer modId;
    private String name;
    private Boolean valid;
    private String url;
    private Integer uid;
    private Integer euid;




    public AdminConfigModuleDetData(final Integer id,final Integer modId,final String name,final Boolean valid,final String url,final Integer uid,final Integer euid){
        this.id = id;
        this.modId = modId;
        this.name = name;
        this.valid = valid;
        this.url = url;
        this.uid = uid;
        this.euid = euid;
    }

    public static AdminConfigModuleDetData newInstance(final Integer id,final Integer modId,final String name,final Boolean valid,final String url,final Integer uid,final Integer euid){
        return new AdminConfigModuleDetData(id,modId,name,valid,url,uid,euid);
    }
}
