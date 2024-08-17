package com.ponsun.san.accessPermission.data;

import lombok.Data;

@Data
public class AccessPermissionData {
    private String name;
    private String code;
    private String link;
    private String uid;
    private String header;

    public AccessPermissionData(final String name,final String code,final String link,final String uid,final String header){
        this.name = name;
        this.code = code;
        this.link = link;
        this.uid = uid;
       this.header = header;
    }
    public static AccessPermissionData newInstance(final String name,final String code,final String link,final String uid,final String header){
        return new AccessPermissionData(name,code,link,uid,header);
    }
}
