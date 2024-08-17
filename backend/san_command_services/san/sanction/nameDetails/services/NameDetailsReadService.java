package com.ponsun.san.sanction.nameDetails.services;

import com.ponsun.san.sanction.nameDetails.data.*;

import java.util.List;

public interface NameDetailsReadService {

    List<WholeNameData> fetchAllWholeNameData(Integer Entity_logical_id);

    List<IdentificationDetailsData> fetchAllIdentificationData(Integer Entity_logical_id_Iden);

    List<AddressFileData> fetchAllAddressData(Integer Entity_logical_id_Iden);


    List<PersonalDetailsData> fetchAllDetailsData(Integer Entity_logical_id);

    List<BirthDetailsData> fetchAllBirthDetailsData(Integer Entity_logical_id_birth);

    List<CityDetailsData> fetchAllCityDetailsData(Integer Entity_logical_id_citi);

    List<AliasesDetailsData> fetchAllAliasesDetailsData(Integer Group_ID);

    List<NationalIdentificationData> fetchAllNationalIdentificationData(Integer Group_ID);
}
