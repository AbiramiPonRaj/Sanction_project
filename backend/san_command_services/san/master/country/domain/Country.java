package com.ponsun.san.master.country.domain;



import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import com.ponsun.san.master.country.request.CreateCountryRequest;
import com.ponsun.san.master.country.request.UpdateCountryRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "country")
public class Country  {
    private static final long serialVersionUID = 1L;

    @Id
    //@Column(name = "id",nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;


    @Column(name = "PrimaryKey")
    private String PrimaryKey;

    @Column(name ="ID")
    private String ID;

    @Column(name = "ISO2")
    private String ISO2;

    @Column(name = "Text")
    private String Text;

    @Column(name = "FK_CountryValues")
    private String FK_CountryValues;

    public static Country create(final CreateCountryRequest createCountryRequest){
        final Country Country = new Country();
        Country.setPrimaryKey(createCountryRequest.getPrimaryKey());
        Country.setID(createCountryRequest.getID());
        Country.setISO2(createCountryRequest.getISO2());
        Country.setFK_CountryValues(createCountryRequest.getFK_CountryValues());
        return Country;
    }
    public void update(UpdateCountryRequest updateCountryRequest){
        this.setPrimaryKey(updateCountryRequest.getPrimaryKey());
        this.setID(updateCountryRequest.getID());
        this.setISO2(updateCountryRequest.getISO2());
        this.setFK_CountryValues(updateCountryRequest.getFK_CountryValues());
    }
}
