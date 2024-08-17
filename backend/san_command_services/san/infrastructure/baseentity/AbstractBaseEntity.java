package com.ponsun.san.infrastructure.baseentity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractBaseEntity extends BaseEntity{

    @Column(name = "is_deleted")
    protected Boolean isDeleted;

    @Column(name = "deleted_on")
    protected LocalDateTime deletedOn;

}
