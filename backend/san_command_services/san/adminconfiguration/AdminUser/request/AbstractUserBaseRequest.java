package com.ponsun.san.adminconfiguration.AdminUser.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public abstract class AbstractUserBaseRequest {
    private Integer id;
    private String userName;
    private String password;
    private String adminGroup;
    private String fullName;
    private LocalDate dob;
    private Long genderId;
    private Long maritalStatusId;
    private LocalDate dom;
    private Long msgDisplayed;
    private Boolean superUser;
    private Long admin;
    private String email;
    private String phoneNo;
    private LocalDate validFrm;
    private LocalDate validTo;
    private Boolean valid;
    private Long orgId;
    private  Integer uid;

}
