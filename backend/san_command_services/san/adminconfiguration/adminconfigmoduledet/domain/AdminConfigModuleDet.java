package com.ponsun.san.adminconfiguration.adminconfigmoduledet.domain;


import com.ponsun.san.adminconfiguration.adminconfigmoduledet.request.CreateAdminConfigModuleDetRequest;
import com.ponsun.san.adminconfiguration.adminconfigmoduledet.request.UpdateAdminConfigModuleDetRequest;
import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "admin_config_module_det")
public class AdminConfigModuleDet extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mod_id")
    private Integer modId;

    @Column(name = "name")
    private String name;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "url")
    private String url;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "euid")
    private Integer euid;


    public static AdminConfigModuleDet create(final CreateAdminConfigModuleDetRequest createAdminConfigModuleDetRequest){
        final  AdminConfigModuleDet adminconfigmoduledet = new AdminConfigModuleDet();
        //adminconfigmoduledet.setId(createAdminConfigModuleDetRequest.getId());
        adminconfigmoduledet.setModId(createAdminConfigModuleDetRequest.getModId());
        adminconfigmoduledet.setName(createAdminConfigModuleDetRequest.getName());
        adminconfigmoduledet.setValid(createAdminConfigModuleDetRequest.getValid());
        adminconfigmoduledet.setUrl(createAdminConfigModuleDetRequest.getUrl());
        adminconfigmoduledet.setUid(createAdminConfigModuleDetRequest.getUid());
        adminconfigmoduledet.setStatus(Status.ACTIVE);
        adminconfigmoduledet.setCreatedAt(LocalDateTime.now());
        return adminconfigmoduledet;
    }
    public void update(final UpdateAdminConfigModuleDetRequest updateAdminConfigModuleDetRequest){
        this.setModId(updateAdminConfigModuleDetRequest.getModId());
        this.setName(updateAdminConfigModuleDetRequest.getName());
        this.setValid(updateAdminConfigModuleDetRequest.getValid());
        this.setUrl(updateAdminConfigModuleDetRequest.getUrl());
        this.setEuid(updateAdminConfigModuleDetRequest.getEuid());
        this.setStatus(Status.ACTIVE);
        this.setCreatedAt(LocalDateTime.now());
    }

//    public void deactive(UpdateAdminConfigModuleDetRequest updateAdminConfigModuleDetRequest){
//        this.setEuid(updateAdminConfigModuleDetRequest.getEuid());
//        this.onUpdate();
//        this.setStatus(Status.DELETE);
//    }
}
