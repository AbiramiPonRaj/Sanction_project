package com.ponsun.san.master.status.domain;

import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import com.ponsun.san.master.status.request.CreateStatusRequest;
import com.ponsun.san.master.status.request.UpdateStatusRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "status")
public class Status extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "valid")
    private Boolean valid;

    public static Status create(final CreateStatusRequest createStatusRequest) {
        final Status status = new Status();
        status.setCode(createStatusRequest.getCode());
        status.setName(createStatusRequest.getName());
        status.setValid(createStatusRequest.getValid());
        status.setStatus(com.ponsun.san.common.entity.Status.ACTIVE);
        status.setCreatedAt(LocalDateTime.now());
        return status;
    }

    public void update(final UpdateStatusRequest updateStatusRequest){
        this.setCode(updateStatusRequest.getCode());
        this.setName(updateStatusRequest.getName());
        this.setValid(updateStatusRequest.getValid());
        this.setStatus(com.ponsun.san.common.entity.Status.ACTIVE);
        this.setCreatedAt(LocalDateTime.now());
    }
}
