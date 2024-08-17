package com.ponsun.san.sanction.nameDetails.api;

import com.ponsun.san.sanction.nameDetails.data.*;
import com.ponsun.san.sanction.nameDetails.services.NameDetailsReadService;
import com.ponsun.san.sanction.nameDetails.services.NameDetailsWriteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/NameDetails")
@Tag(name="EU_DetailsApiResource")
public class NameDetailsApiResources {

    private final NameDetailsWriteService nameDetailsWriteService;
    private final NameDetailsReadService nameDetailsReadService;

    @GetMapping("/AKADetails")
    public List<WholeNameData> fetchAllWholeNameData(@RequestParam Integer Entity_logical_id) {
        return this.nameDetailsReadService.fetchAllWholeNameData(Entity_logical_id);
    }

    @GetMapping("/Identification")
    public List<IdentificationDetailsData> fetchAllIdentificationData(@RequestParam Integer Entity_logical_id_Iden) {
        return this.nameDetailsReadService.fetchAllIdentificationData(Entity_logical_id_Iden);
    }
    @GetMapping("/Address")
    public List<AddressFileData> fetchAllAddressData(@RequestParam Integer Entity_logical_id_Addr) {
        return this.nameDetailsReadService.fetchAllAddressData(Entity_logical_id_Addr);
    }
    @GetMapping("/Details")
    public List<PersonalDetailsData> fetchAllDetailsData(@RequestParam Integer Entity_logical_id) {
        return this.nameDetailsReadService.fetchAllDetailsData(Entity_logical_id);
    }
    @GetMapping("/BirthDetails")
    public List<BirthDetailsData> fetchAllBirthDetailsData(@RequestParam Integer Entity_logical_id_birth) {
        return this.nameDetailsReadService.fetchAllBirthDetailsData(Entity_logical_id_birth);
    }

    @GetMapping("/CityDetails")
    public List<CityDetailsData> fetchAllCityDetails(@RequestParam Integer Entity_logical_id_citi) {
        return this.nameDetailsReadService.fetchAllCityDetailsData(Entity_logical_id_citi);
    }

    @GetMapping("/AliasesDetails")
    public List<AliasesDetailsData> fetchAllAliasesDetails(@RequestParam Integer Group_ID) {
        return this.nameDetailsReadService.fetchAllAliasesDetailsData(Group_ID);
    }

    @GetMapping("/NationalIdentification")
    public List<NationalIdentificationData> fetchAllNationalIdentificationData(@RequestParam Integer Group_ID) {
        return this.nameDetailsReadService.fetchAllNationalIdentificationData(Group_ID);
    }
}

