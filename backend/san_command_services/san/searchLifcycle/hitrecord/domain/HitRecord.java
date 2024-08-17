package com.ponsun.san.searchLifcycle.hitrecord.domain;

import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import com.ponsun.san.searchLifcycle.hitrecord.request.CreateHitRecordRequest;
import com.ponsun.san.searchLifcycle.hitrecord.request.UpdateHitRecordRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;


@Data
@Entity
@Accessors(chain = true)
@Table(name = "hitrecord")
public class HitRecord extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * san_search
     */
    @Column(name = "searchId")
    private Integer searchId;

    @Column(name = "display")
    private String display;

    @Column(name = "criminalId")
    private Integer criminalId;

    @Column(name="fileType")
    private Integer fileType;

    @Column(name = "matchingScore")
    private Double matchingScore;

    @Column(name = "name")
    private String name;

    /**
     * san_config_status
     */
    @Column(name = "statusNowId")
    private Integer statusNowId;

    @Column(name = "cycleId")
    private Integer cycleId;

    @Column(name = "uid")
    private Long uid;


    @Column(name = "valid")
    private Boolean valid;

    public static HitRecord create(final CreateHitRecordRequest createHitDataRequest) {
        final HitRecord hitRecord = new HitRecord();
//        hitData.setId(createHitDataRequest.getId());
        hitRecord.setSearchId(createHitDataRequest.getSearchId());
        hitRecord.setDisplay(createHitDataRequest.getDisplay());
        hitRecord.setCriminalId(createHitDataRequest.getCriminalId());
        hitRecord.setFileType(createHitDataRequest.getFileType());
        hitRecord.setMatchingScore(createHitDataRequest.getMatchingScore());
        hitRecord.setName(createHitDataRequest.getName());
        hitRecord.setStatusNowId(createHitDataRequest.getStatusNowId());
        hitRecord.setCycleId(createHitDataRequest.getCycleId());
        hitRecord.setValid(createHitDataRequest.getValid());
        hitRecord.setStatus(Status.ACTIVE);
        return hitRecord;
    }
    public void update(final UpdateHitRecordRequest updateHitDataRequest){
        this.setSearchId(updateHitDataRequest.getSearchId());
        this.setDisplay(updateHitDataRequest.getDisplay());
        this.setCriminalId(updateHitDataRequest.getCriminalId());
        this.setFileType(updateHitDataRequest.getFileType());
        this.setMatchingScore(updateHitDataRequest.getMatchingScore());
        this.setName(updateHitDataRequest.getName());
        this.setStatusNowId(updateHitDataRequest.getStatusNowId());
        this.setCycleId(updateHitDataRequest.getCycleId());
        this.setValid(updateHitDataRequest.getValid());
        this.setStatus(Status.ACTIVE);
        this.setCreatedAt(LocalDateTime.now());
    }
}

