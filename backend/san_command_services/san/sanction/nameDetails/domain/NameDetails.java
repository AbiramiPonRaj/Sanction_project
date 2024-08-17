package com.ponsun.san.sanction.nameDetails.domain;


import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import com.ponsun.san.sanction.nameDetails.request.CreateNameDetailsRequest;
import com.ponsun.san.sanction.nameDetails.request.UpdateNameDetailsRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "")
public class NameDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "Title")
    private String Title;

    @Column(name = "Name_1")
    private String Name_1;

    @Column(name = "Gender")
    private String Gender;

    @Column(name = "DOB")
    private String DOB;

    @Column(name = "Town_of_Birth")
    private String Town_of_Birth;

    public static NameDetails create(final CreateNameDetailsRequest createNameDetailsRequest){
        final NameDetails nameDetails = new NameDetails();
        nameDetails.setTitle(createNameDetailsRequest.getTitle());
        nameDetails.setName_1(createNameDetailsRequest.getName_1());
        nameDetails.setGender(createNameDetailsRequest.getGender());
        nameDetails.setDOB(createNameDetailsRequest.getDOB());
        nameDetails.setTown_of_Birth(createNameDetailsRequest.getTown_of_Birth());
        nameDetails.onCreate();
        nameDetails.setStatus(Status.ACTIVE);
        return nameDetails;
    }

    public void update(UpdateNameDetailsRequest updateNameDetailsRequest){
        this.setTitle(updateNameDetailsRequest.getTitle());
        this.setName_1(updateNameDetailsRequest.getName_1());
        this.setGender(updateNameDetailsRequest.getGender());
        this.setDOB(updateNameDetailsRequest.getDOB());
        this.setTown_of_Birth(updateNameDetailsRequest.getTown_of_Birth());
        this.onUpdate();
        this.setStatus(Status.ACTIVE);
    }
}
