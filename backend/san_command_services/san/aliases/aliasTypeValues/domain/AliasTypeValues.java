package com.ponsun.san.aliases.aliasTypeValues.domain;


import com.ponsun.san.aliases.aliasTypeValues.request.CreateAliasTypeValuesRequest;
import com.ponsun.san.aliases.aliasTypeValues.request.UpdateAliasTypeValuesRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "aliastypevalues")
public class AliasTypeValues  {

    private static final long serialVersionUID = 1L;

    @Id
    //@Column(name = "id",nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;

    @Column(name = "PrimaryKey")
    private String PrimaryKey;

    @Column(name = "FK_ReferenceValueSets")
    private String FK_ReferenceValueSets;

    public static AliasTypeValues create(final CreateAliasTypeValuesRequest createAliasTypeValuesRequest){
        final AliasTypeValues AliasTypeValues = new AliasTypeValues();
        AliasTypeValues.setPrimaryKey(createAliasTypeValuesRequest.getPrimaryKey());
        AliasTypeValues.setFK_ReferenceValueSets(createAliasTypeValuesRequest.getFK_ReferenceValueSets());
        return AliasTypeValues;
    }
    public void update(UpdateAliasTypeValuesRequest updateAliasTypeValuesRequest){
        this.setPrimaryKey(updateAliasTypeValuesRequest.getPrimaryKey());
        this.setFK_ReferenceValueSets(updateAliasTypeValuesRequest.getFK_ReferenceValueSets());
    }
}
