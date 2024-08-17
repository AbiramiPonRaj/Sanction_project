package com.ponsun.san.master.sanctionsProgram.domain;



import com.ponsun.san.master.sanctionsProgram.request.CreateSanctionsProgramRequest;
import com.ponsun.san.master.sanctionsProgram.request.UpdateSanctionsProgramRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "sanctionsprogram")
public class SanctionsProgram {
    private static final long serialVersionUID = 1L;

    @Id
    //@Column(name = "id",nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;


    @Column(name = "PrimaryKey")
    private String PrimaryKey;

    @Column(name ="ID")
    private String ID;

    @Column(name ="SubsidiaryBodyID")
    private String SubsidiaryBodyID;

    @Column(name = "Text")
    private String Text;

    @Column(name = "FK_SanctionsProgramValues")
    private String FK_SanctionsProgramValues;

    public static SanctionsProgram create(final CreateSanctionsProgramRequest createSanctionsProgramRequest){
        final SanctionsProgram sanctionsProgram = new SanctionsProgram();
        sanctionsProgram.setPrimaryKey(createSanctionsProgramRequest.getPrimaryKey());
        sanctionsProgram.setID(createSanctionsProgramRequest.getID());
        sanctionsProgram.setSubsidiaryBodyID(createSanctionsProgramRequest.getSubsidiaryBodyID());
        sanctionsProgram.setFK_SanctionsProgramValues(createSanctionsProgramRequest.getFK_SanctionsProgramValues());
        return sanctionsProgram;
    }
    public void update(UpdateSanctionsProgramRequest updateSanctionsProgramRequest){
        this.setPrimaryKey(updateSanctionsProgramRequest.getPrimaryKey());
        this.setID(updateSanctionsProgramRequest.getID());
        this.setSubsidiaryBodyID(updateSanctionsProgramRequest.getSubsidiaryBodyID());
        this.setFK_SanctionsProgramValues(updateSanctionsProgramRequest.getFK_SanctionsProgramValues());
    }
}
