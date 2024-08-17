


package com.ponsun.san.ofac.Address.api;
import com.ponsun.san.ofac.Address.data.AddressData;
import com.ponsun.san.ofac.Address.services.AddressWriteService;
import com.ponsun.san.ofac.Program.data.ProgramData;
import com.ponsun.san.ofac.Program.services.ProgramWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/AddressApiResources")
public class AddressApiResources {
    private final AddressWriteService addressWriteService;

    @GetMapping
    public List<AddressData> fetchAll(@RequestParam String id){
        return this.addressWriteService.fetchAllAddressData(id);
    }

}
