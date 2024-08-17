package com.ponsun.san.adminconfiguration.adminuserauthority.data;

import lombok.Data;

@Data
public class AdminUserAuthorityData {
    private Integer id;
    private String roleCode;
    private String roleDescription;
    private boolean valid;
    private Integer uid;
    private  Integer euid;

    public AdminUserAuthorityData (final Integer id ,final String roleCode,final String roleDescription,final boolean valid,final Integer uid,final Integer euid){
        this.id = id;
        this.roleCode = roleCode;
        this.roleDescription = roleDescription;
        this.valid = valid;
        this.uid = uid;
        this.euid = euid;
    }
    public static AdminUserAuthorityData newInstance (final Integer id ,final String roleCode,final String roleDescription,final boolean valid,final Integer uid,final Integer euid) {
        return new AdminUserAuthorityData(id,roleCode,roleDescription,valid,uid,euid);
    }
}
