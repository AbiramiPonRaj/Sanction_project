package com.ponsun.san.adminconfiguration.AdminUser.data;

import lombok.Data;

@Data
public class UserData {
    private Integer id;
    private String userName;
    private String password;
    private String adminGroup;
    private String fullName;
    private String dob;
    private Long genderId;
    private Long maritalStatusId;
    private String dom;
    private Long msgDisplayed;
    private Boolean superUser;
    private Long admin;
    private String email;
    private String phoneNo;
    private String validFrm;
    private String validTo;
    private Boolean valid;
    private Long orgId;
    private Integer uid;


    public UserData(final Integer id,final String userName, final String password, final String adminGroup, final String fullName, final String dob, final Long genderId, final Long maritalStatusId, final String dom, final Long msgDisplayed, final Boolean superUser, final Long admin, final String email, final String phoneNo, final String validFrm, final String validTo, final Boolean valid, final Long orgId,final Integer uid) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.adminGroup = adminGroup;
        this.fullName = fullName;
        this.dob = dob;
        this.genderId = genderId;
        this.maritalStatusId = maritalStatusId;
        this.dom = dom;
        this.msgDisplayed = msgDisplayed;
        this.superUser = superUser;
        this.admin = admin;
        this.email = email;
        this.phoneNo = phoneNo;
        this.validFrm = validFrm;
        this.validTo = validTo;
        this.valid = valid;
        this.orgId = orgId;
        this.uid = uid;

    }

    public static UserData newInstance(final Integer id,final String userName, final String password, final String adminGroup, final String fullName, final String dob, final Long genderId, final Long maritalStatusId, final String dom, final Long msgDisplayed, final Boolean superUser, final Long admin, final String email, final String phoneNo, final String validFrm, final String validTo, final Boolean valid, final Long orgId,final Integer uid) {
        return new UserData(id,userName ,password, adminGroup, fullName, dob, genderId, maritalStatusId, dom, msgDisplayed, superUser, admin, email, phoneNo, validFrm, validTo, valid, orgId,uid);
    }
}
