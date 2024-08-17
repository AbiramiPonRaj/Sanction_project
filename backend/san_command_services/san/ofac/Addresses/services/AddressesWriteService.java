package com.ponsun.san.ofac.Addresses.services;
import com.ponsun.san.ofac.Addresses.data.AddressesData;

import java.util.List;

public interface AddressesWriteService {
    List<AddressesData> fetchAllAddressesData(Integer id);

}
