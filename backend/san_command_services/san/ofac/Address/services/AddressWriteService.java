//package com.ponsun.san.ofac.Address.services;
//
//public class AddressWriteService {
//}



package com.ponsun.san.ofac.Address.services;

import com.ponsun.san.ofac.Address.data.AddressData;
import com.ponsun.san.ofac.Program.data.ProgramData;

import java.util.List;

public interface AddressWriteService {
    List<AddressData> fetchAllAddressData(String id);
}
