package com.ponsun.san.ofac.Addresses.api;

import com.ponsun.san.ofac.Addresses.data.AddressesData;
import com.ponsun.san.ofac.Addresses.services.AddressesWriteService;
import com.ponsun.san.ofac.Aliases.data.AliasesData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/AddressesApiresources")
public class AddressesApiresources {
    private final AddressesWriteService addressesWriteService;

    @GetMapping
    public List<AddressesData> fetchAll(@RequestParam Integer id){
        return this.addressesWriteService.fetchAllAddressesData(id);
    }
}
