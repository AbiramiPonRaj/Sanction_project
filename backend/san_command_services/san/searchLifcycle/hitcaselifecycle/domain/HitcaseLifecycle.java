package com.ponsun.san.searchLifcycle.hitcaselifecycle.domain;

import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.request.CreateHitCaseLifeCycleRequest;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.request.UpdateHitCaseLifeCycleRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "hitcase_lifecycle")
public class HitcaseLifecycle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "case_id")
    private Integer caseId;

    @Column(name = "level_id")
    private Integer levelId;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "uid")
    private Integer uid;

//    @Column(name = "dt")
//    private String dt;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "remark")
    private String remark;

    public static HitcaseLifecycle create(final CreateHitCaseLifeCycleRequest createHitCaseLifeCycleRequest) {
        final HitcaseLifecycle hitcaseLifecycle = new HitcaseLifecycle();
        hitcaseLifecycle.setCaseId(createHitCaseLifeCycleRequest.getCaseId());
        hitcaseLifecycle.setLevelId(createHitCaseLifeCycleRequest.getLevelId());
        hitcaseLifecycle.setRemark(createHitCaseLifeCycleRequest.getRemark());
        hitcaseLifecycle.setStatusId(createHitCaseLifeCycleRequest.getStatusId());
        hitcaseLifecycle.setUid(createHitCaseLifeCycleRequest.getUid());
        hitcaseLifecycle.setValid(createHitCaseLifeCycleRequest.getValid());
//        hitcaseLifecycle.setDt(createHitCaseLifeCycleRequest.getDt());
        hitcaseLifecycle.setStatus(Status.ACTIVE);
        return hitcaseLifecycle;
    }
    public void update(final UpdateHitCaseLifeCycleRequest updateHitCaseLifeCycleRequest){
        this.setCaseId(updateHitCaseLifeCycleRequest.getCaseId());
        this.setLevelId(updateHitCaseLifeCycleRequest.getLevelId());
        this.setRemark(updateHitCaseLifeCycleRequest.getRemark());
        this.setStatusId(updateHitCaseLifeCycleRequest.getStatusId());
        this.setUid(updateHitCaseLifeCycleRequest.getUid());
        this.setValid(updateHitCaseLifeCycleRequest.getValid());
        this.setStatus(Status.ACTIVE);
        this.setCreatedAt(LocalDateTime.now());
    }

}
