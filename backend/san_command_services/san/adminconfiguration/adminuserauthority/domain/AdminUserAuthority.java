package com.ponsun.san.adminconfiguration.adminuserauthority.domain;


import com.ponsun.san.adminconfiguration.adminuserauthority.request.CreateAdminUserAuthorityRequest;
import com.ponsun.san.adminconfiguration.adminuserauthority.request.UpdateAdminUserAuthorityRequest;
import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "admin_user_authority")
public class AdminUserAuthority extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "role_description")
    private String roleDescription;

    @Column(name =  "valid")
    private boolean valid;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "euid")
    private Integer euid;

    public static AdminUserAuthority create(final CreateAdminUserAuthorityRequest createAdminUserAuthorityRequest){
        final AdminUserAuthority adminUserAuthority = new AdminUserAuthority();
            //adminUserAuthority.setId(createAdminUserAuthorityRequest.getId());
            adminUserAuthority.setRoleCode(createAdminUserAuthorityRequest.getRoleCode());
            adminUserAuthority.setRoleDescription(createAdminUserAuthorityRequest.getRoleDescription());
            adminUserAuthority.setValid(createAdminUserAuthorityRequest.getValid());
            adminUserAuthority.setUid(createAdminUserAuthorityRequest.getUid());
            adminUserAuthority.setStatus(Status.ACTIVE);
            adminUserAuthority.setCreatedAt(LocalDateTime.now());
            return adminUserAuthority;
    }
    public void update(final UpdateAdminUserAuthorityRequest updateAdminUserAuthorityRequest){
        this.setRoleCode(updateAdminUserAuthorityRequest.getRoleCode());
        this.setRoleDescription(updateAdminUserAuthorityRequest.getRoleDescription());
        this.setValid(updateAdminUserAuthorityRequest.getValid());
        this.setEuid(updateAdminUserAuthorityRequest.getEuid());
        this.setStatus(Status.ACTIVE);
        this.setUpdatedAt(LocalDateTime.now());
    }

    public void deactive(final UpdateAdminUserAuthorityRequest updateAdminUserAuthorityRequest){
        this.setEuid(updateAdminUserAuthorityRequest.getEuid());
        this.onUpdate();
        this.setStatus(Status.DELETE);
    }
}
