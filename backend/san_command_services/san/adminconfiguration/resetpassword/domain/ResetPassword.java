package com.ponsun.san.adminconfiguration.resetpassword.domain;


import com.ponsun.san.adminconfiguration.resetpassword.request.CreateResetPasswordRequest;
import com.ponsun.san.adminconfiguration.resetpassword.request.UpdateResetPasswordRequest;
import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)

@Table(name = "admin_users_reset_password")
public class ResetPassword extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reset_uid", nullable = false)
    private Long resetUid;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "changed_uid", nullable = false)
    private Integer changedUid;

    @Column(name = "changed_date", nullable = false)
    private String changedDate;

    @Column(name = "changed_time", nullable = false)
    private String changedTime;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "euid")
    private Integer euid;

    public static ResetPassword create(final CreateResetPasswordRequest createResetPasswordRequest){
       final ResetPassword resetPassword = new ResetPassword();
//      resetPassword.setId(createResetPasswordRequest.getId());
        resetPassword.setResetUid(createResetPasswordRequest.getResetUid());
        resetPassword.setPassword(createResetPasswordRequest.getPassword());
        resetPassword.setReason(createResetPasswordRequest.getReason());
        resetPassword.setChangedUid(createResetPasswordRequest.getChangedUid());
        resetPassword.setChangedDate(createResetPasswordRequest.getChangedDate());
        resetPassword.setChangedTime(createResetPasswordRequest.getChangedTime());
        resetPassword.setUid(createResetPasswordRequest.getUid());
        resetPassword.setStatus(Status.ACTIVE);
        return resetPassword;
    }
    public void update(final UpdateResetPasswordRequest updateResetPasswordRequest){
        this.setResetUid(updateResetPasswordRequest.getResetUid());
        this.setPassword(updateResetPasswordRequest.getPassword());
        this.setReason(updateResetPasswordRequest.getReason());
        this.setChangedUid(updateResetPasswordRequest.getChangedUid());
        this.setChangedDate(updateResetPasswordRequest.getChangedDate());
        this.setChangedTime(updateResetPasswordRequest.getChangedTime());
        this.setEuid(updateResetPasswordRequest.getEuid());
        this.setStatus(Status.ACTIVE);
        this.setCreatedAt(LocalDateTime.now());
    }
    public void deactive(final UpdateResetPasswordRequest updateResetPasswordRequest){
        this.setEuid(updateResetPasswordRequest.getEuid());
        this.onUpdate();
        this.setStatus(Status.DELETE);
    }

}
