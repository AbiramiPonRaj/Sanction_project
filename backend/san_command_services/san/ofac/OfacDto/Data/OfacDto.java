package com.ponsun.san.ofac.OfacDto.Data;

import com.ponsun.san.ofac.Addresses.data.AddressesData;
import com.ponsun.san.ofac.Aliases.data.AliasesData;
import com.ponsun.san.ofac.Details.data.DetailsData;
import com.ponsun.san.ofac.identification.data.IdentificationData;
import lombok.Data;

import java.util.List;
@Data
public class OfacDto {
    private DetailsData detailsData;
    private List<IdentificationData> identificationData;
    private List<AliasesData> aliasesData;
    private List<AddressesData> addressesData;

    public OfacDto(DetailsData detailsData, List<IdentificationData> identificationData, List<AliasesData> aliasesData, List<AddressesData> addressesData) {
        this.detailsData = detailsData;
        this.identificationData = identificationData;
        this.aliasesData = aliasesData;
        this.addressesData = addressesData;
    }
    public static OfacDto newInstance(DetailsData detailsData, List<IdentificationData> identificationData, List<AliasesData> aliasesData, List<AddressesData> addressesData){
        return new OfacDto(detailsData,identificationData,aliasesData,addressesData);
    }

}




