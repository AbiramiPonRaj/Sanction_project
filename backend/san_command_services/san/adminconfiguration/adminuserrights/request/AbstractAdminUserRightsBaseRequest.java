package com.ponsun.san.adminconfiguration.adminuserrights.request;

import lombok.Data;

@Data
public abstract class AbstractAdminUserRightsBaseRequest {
//    private Integer id;
    private String uid;
    private Integer modId;
    private Integer modDetId;
    private Integer entUid;
    private String dt;
    private Boolean valid;
    private Integer euid;
}
