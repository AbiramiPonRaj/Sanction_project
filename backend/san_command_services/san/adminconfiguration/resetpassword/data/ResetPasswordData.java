package com.ponsun.san.adminconfiguration.resetpassword.data;

import lombok.Data;


@Data
public class ResetPasswordData {
    private Long id;
    private Long resetUid;
    private String password;
    private String reason;
    private Integer changedUid;
    private String changedDate;
    private String changedTime;
    private Integer uid;
    private Integer euid;


    public ResetPasswordData(final Long id, final Long resetUid, final String password, final String reason, final Integer changedUid, final String changedDate, final String changedTime,final Integer uid,final Integer euid){
        this.id =id;
        this.resetUid=resetUid;
        this.password=password;
        this.reason=reason;
        this.changedUid=changedUid;
        this.changedDate=changedDate;
        this.changedTime=changedTime;
        this.uid = uid;
        this.euid = euid;

    }
    public static ResetPasswordData newInstance(final Long id, final Long resetUid, final String password, final String reason, final Integer changedUid, final String changedDate, final String changedTime,final Integer uid,final Integer euid) {
        return new ResetPasswordData(id,resetUid,password,reason,changedUid,changedDate,changedTime,uid,euid);
    }

}
