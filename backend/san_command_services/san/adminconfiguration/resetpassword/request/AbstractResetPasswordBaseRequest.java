package com.ponsun.san.adminconfiguration.resetpassword.request;

import lombok.Data;

@Data
public class AbstractResetPasswordBaseRequest {
    private Long id;
    private Long resetUid;
    private String password;
    private String reason;
    private Integer changedUid;
    private String changedDate;
    private String changedTime;
    private Integer uid;
    private  Integer euid;
}


