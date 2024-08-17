package com.ponsun.san.searchLifcycle.hitcase.domain;

import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import com.ponsun.san.searchLifcycle.hitcase.request.CreateHitCaseRequest;
import com.ponsun.san.searchLifcycle.hitcase.request.UpdateHitCaseRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "hitcase")
public class Hitcase extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "display")
    private String display;

    /**
     * san_search
     */
    @Column(name = "search_id")
    private Integer searchId;

    /**
     * san_hitdata_lifecycle
     */
    @Column(name = "hit_id")
    private Integer hitId;

    @Column(name = "criminal_id")
    private Integer criminalId;

    /**
     * san_config_level
     */
    @Column(name = "level_id")
    private Integer levelId;

    /**
     * san_config_status
     */
    @Column(name = "status_now_id")
    private Integer statusNowId;

    @Column(name = "cycle_id")
    private Integer cycleId;

    @Column(name = "uid")
    private Integer uid;

//    @Column(name = "dt")
//    private String dt;

    @Column(name = "valid")
    private Boolean valid;

    public static Hitcase create (final CreateHitCaseRequest createHitCaseRequest) {

        final Hitcase hitcase = new Hitcase();
//        hitcase.setId(createHitCaseRequest.getId());
        hitcase.setDisplay(createHitCaseRequest.getDisplay());
        hitcase.setSearchId(createHitCaseRequest.getSearchId());
        hitcase.setHitId(createHitCaseRequest.getHitId());
        hitcase.setCriminalId(createHitCaseRequest.getCriminalId());
        hitcase.setLevelId(createHitCaseRequest.getLevelId());
        hitcase.setStatusNowId(createHitCaseRequest.getStatusNowId());
        hitcase.setCycleId(createHitCaseRequest.getCycleId());
        hitcase.setUid(createHitCaseRequest.getUid());
        hitcase.setStatus(Status.ACTIVE);
        hitcase.onCreate();
//        crmHitcase.setDt(createHitCaseRequest.getDt());
        hitcase.setValid(true);
        return hitcase;
    }
    public void update(final UpdateHitCaseRequest updateHitCaseRequest){
        this.setDisplay(updateHitCaseRequest.getDisplay());
        this.setSearchId(updateHitCaseRequest.getSearchId());
        this.setHitId(updateHitCaseRequest.getHitId());
        this.setCriminalId(updateHitCaseRequest.getCriminalId());
        this.setLevelId(updateHitCaseRequest.getLevelId());
        this.setStatusNowId(updateHitCaseRequest.getStatusNowId());
        this.setCycleId(updateHitCaseRequest.getCycleId());
        this.setUid(updateHitCaseRequest.getUid());
        this.setValid(updateHitCaseRequest.getValid());

        this.setStatus(Status.ACTIVE);
        this.setCreatedAt(LocalDateTime.now());
    }
}
