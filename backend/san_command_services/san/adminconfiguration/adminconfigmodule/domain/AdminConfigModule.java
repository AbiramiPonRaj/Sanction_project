package com.ponsun.san.adminconfiguration.adminconfigmodule.domain;


import com.ponsun.san.adminconfiguration.adminconfigmodule.request.CreateAdminConfigModuleRequest;
import com.ponsun.san.adminconfiguration.adminconfigmodule.request.UpdateAdminConfigModuleRequest;
import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "admin_config_module")
public class AdminConfigModule extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "orderno")
    private Integer orderno;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "euid")
    private Integer euid;


    public static AdminConfigModule create (final CreateAdminConfigModuleRequest createAdminConfigModuleRequest) {
        final AdminConfigModule adminConfigModule = new AdminConfigModule();
    //      adminConfigModule.setId(createAdminConfigModuleRequest.getId());
            adminConfigModule.setName(createAdminConfigModuleRequest.getName());
            adminConfigModule.setValid(createAdminConfigModuleRequest.getValid());
            adminConfigModule.setOrderno(createAdminConfigModuleRequest.getOrderno());
            adminConfigModule.setUid(createAdminConfigModuleRequest.getUid());
            adminConfigModule.setStatus(Status.ACTIVE);
            adminConfigModule.setCreatedAt(LocalDateTime.now());
            return adminConfigModule;
    }
    public void update(final UpdateAdminConfigModuleRequest updateAdminConfigModuleRequest){
       this.setName(updateAdminConfigModuleRequest.getName());
       this.setValid(updateAdminConfigModuleRequest.getValid());
       this.setOrderno(updateAdminConfigModuleRequest.getOrderno());
       this.setEuid(updateAdminConfigModuleRequest.getEuid());
       this.setStatus(Status.ACTIVE);
       this.setUpdatedAt(LocalDateTime.now());
}

//public void deactive(final Integer euid){
//        this.setEuid(euid);
//        this.setStatus(Status.DELETE);
//        this.onUpdate();
//}
}

