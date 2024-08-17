package com.ponsun.san.adminconfiguration.AdminUser.domain;

import com.ponsun.san.adminconfiguration.AdminUser.request.CreateUserRequest;
import com.ponsun.san.adminconfiguration.AdminUser.request.UpdateUserRequest;
import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Entity(name = "Users")
@Accessors(chain = true)
@Table(name = "admin_users")
//@JsonIgnoreProperties({"updatedBy"})
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password"  )
    private String password;

    @Column(name = "admin_grp" )
    private String adminGroup;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "sex")
    private Long genderId;

    @Column(name = "marital_status")
    private Long maritalStatusId;

    @Column(name = "dom")
    private LocalDate dom;

    @Column(name = "is_msg_displayed")
    private Long msgDisplayed;

    @Column(name = "is_superUser")
    private Boolean superUser;

    @Column(name = "is_admin")
    private Long admin;

    @Column(name = "email")
    private String email;

    @Column(name = "phno")
    private String phoneNo;

    @Column(name = "validFrm")
    private LocalDate validFrm;

    @Column(name = "validTo")
    private LocalDate validTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="euid")
    private User updatedBy;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "uid")
    private Integer uid;
    public static User create(final CreateUserRequest createUserRequest) {
        final User user = new User();
        user.setUserName(createUserRequest.getUserName());
        user.setAdmin(createUserRequest.getAdmin());
        user.setAdminGroup(createUserRequest.getAdminGroup());
        user.setEmail(createUserRequest.getEmail());
        user.setDob(createUserRequest.getDob());
        user.setDom(createUserRequest.getDom());
        user.setStatus(Status.ACTIVE);
        user.setCreatedAt(LocalDateTime.now());
        user.setFullName(createUserRequest.getFullName());
        user.setValidFrm(createUserRequest.getValidFrm());
        user.setValidTo(createUserRequest.getValidTo());
        user.setValid(createUserRequest.getValid());
        user.setGenderId(createUserRequest.getGenderId());
        user.setPhoneNo(createUserRequest.getPhoneNo());
        user.setMaritalStatusId(createUserRequest.getMaritalStatusId());
        user.setOrgId(createUserRequest.getOrgId());
        user.setPassword(createUserRequest.getPassword());
        user.setSuperUser(createUserRequest.getSuperUser());
        user.setMsgDisplayed(createUserRequest.getMsgDisplayed());
        user.setUid(createUserRequest.getUid());
        return user;
    }

    public void update(final UpdateUserRequest updateUserRequest){
        this.setUserName(updateUserRequest.getUserName());
        this.setAdmin(updateUserRequest.getAdmin());
        this.setAdminGroup(updateUserRequest.getAdminGroup());
        this.setEmail(updateUserRequest.getEmail());
        this.setDob(updateUserRequest.getDob());
        this.setDom(updateUserRequest.getDom());
        this.setCreatedAt(LocalDateTime.now());
        this.setFullName(updateUserRequest.getFullName());
        this.setValidFrm(updateUserRequest.getValidFrm());
        this.setValidTo(updateUserRequest.getValidTo());
        this.setValid(updateUserRequest.getValid());
        this.setGenderId(updateUserRequest.getGenderId());
        this.setPhoneNo(updateUserRequest.getPhoneNo());
        this.setMaritalStatusId(updateUserRequest.getMaritalStatusId());
        this.setOrgId(updateUserRequest.getOrgId());
        this.setPassword(updateUserRequest.getPassword());
        this.setSuperUser(updateUserRequest.getSuperUser());
        this.setMsgDisplayed(updateUserRequest.getMsgDisplayed());
        this.setStatus(Status.ACTIVE);
        this.setCreatedAt(LocalDateTime.now());
        this.setUid(updateUserRequest.getUid());
    }
}



