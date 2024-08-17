package com.ponsun.san.aliases.alias.domain;




import com.ponsun.san.aliases.alias.request.CreateAliasRequest;
import com.ponsun.san.aliases.alias.request.UpdateAliasRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "alias")
public class Alias  {
    private static final long serialVersionUID = 1L;

    @Id
    //@Column(name = "id",nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;


    @Column(name = "PrimaryKey")
    private String PrimaryKey;

    @Column(name ="FixedRef")
    private String FixedRef;

    @Column(name = "AliasTypeID")
    private String AliasTypeID;

    @Column(name = "Primary")
    private String Primary;

    @Column(name = "LowQuality")
    private String LowQuality;

    @Column(name = "FK_Identity")
    private String FK_Identity;

    public static Alias create(final CreateAliasRequest createAliasRequest){
        final Alias Alias = new Alias();
        Alias.setPrimaryKey(createAliasRequest.getPrimaryKey());
        Alias.setFixedRef(createAliasRequest.getFixedRef());
        Alias.setAliasTypeID(createAliasRequest.getAliasTypeID());
        Alias.setPrimary(createAliasRequest.getPrimary());
        Alias.setLowQuality(createAliasRequest.getLowQuality());
        Alias.setFK_Identity(createAliasRequest.getFK_Identity());
        return Alias;
    }
    public void update(UpdateAliasRequest updateAliasRequest){
        this.setPrimaryKey(updateAliasRequest.getPrimaryKey());
        this.setFixedRef(updateAliasRequest.getFixedRef());
        this.setAliasTypeID(updateAliasRequest.getAliasTypeID());
        this.setPrimary(updateAliasRequest.getPrimary());
        this.setLowQuality(updateAliasRequest.getLowQuality());
        this.setFK_Identity(updateAliasRequest.getFK_Identity());
    }
}
