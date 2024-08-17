package com.ponsun.san.adminconfiguration.adminuserrights.data;

import lombok.Data;

@Data
public class AdminUserRightsData {
    private Integer id;
    private String uid;
    private Integer modId;
    private Integer modDetId;
    private Integer entUid;
    private String dt;
    private Boolean valid;
    private Integer euid;

    public AdminUserRightsData(final Integer id,final String uid,final Integer modId,final Integer modDetId,final Integer entUid, final String dt,final Boolean valid,final Integer euid) {
        this.id = id;
        this.uid = uid;
        this.modId = modId;
        this.modDetId = modDetId;
        this.entUid = entUid;
        this.dt =dt;
        this.valid = valid;
        this.euid = euid;
    }
    public static AdminUserRightsData newInstance (final Integer id,final String uid,final Integer modId,final Integer modDetId,final Integer entUid, final String dt,final Boolean valid,final Integer euid){
        return new AdminUserRightsData(id,uid,modId,modDetId ,entUid,dt,valid,euid);
    }

}
