package com.ponsun.san.adminconfiguration.adminuserauthority.request;
import lombok.Data;

@Data
public class AbstractAdminUserAuthorityBaseRequest {
//    private Integer id;
    private String roleCode;
    private String roleDescription;
    private Boolean valid;
    private Integer uid;
    private Integer euid;
}
