package com.ponsun.san.aliases.aliasType.domain;


import com.ponsun.san.aliases.aliasType.request.CreateAliasTypeRequest;
import com.ponsun.san.aliases.aliasType.request.UpdateAliasTypeRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "aliastype")
public class AliasType {
    private static final long serialVersionUID = 1L;

    @Id
    //@Column(name = "id",nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;


    @Column(name = "PrimaryKey")
    private String PrimaryKey;

    @Column(name ="ID")
    private String ID;

    @Column(name = "Text")
    private String Text;

    @Column(name = "FK_AliasTypeValues")
    private String FK_AliasTypeValues;

    public static AliasType create(final CreateAliasTypeRequest createAliasTypeRequest){
        final AliasType AliasTypes = new AliasType();
        AliasTypes.setPrimaryKey(createAliasTypeRequest.getPrimaryKey());
        AliasTypes.setID(createAliasTypeRequest.getID());
        AliasTypes.setText(createAliasTypeRequest.getID());
        AliasTypes.setFK_AliasTypeValues(createAliasTypeRequest.getFK_AliasTypeValues());
        return AliasTypes;
    }
    public void update(UpdateAliasTypeRequest updateAliasTypeRequest){
        this.setPrimaryKey(updateAliasTypeRequest.getPrimaryKey());
        this.setID(updateAliasTypeRequest.getID());
        this.setText(updateAliasTypeRequest.getID());
        this.setFK_AliasTypeValues(updateAliasTypeRequest.getFK_AliasTypeValues());
    }
}
