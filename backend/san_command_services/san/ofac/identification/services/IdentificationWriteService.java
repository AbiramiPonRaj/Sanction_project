package com.ponsun.san.ofac.identification.services;

import com.ponsun.san.ofac.identification.data.IdentificationData;

import java.util.List;

public interface IdentificationWriteService {
    List<IdentificationData> fetchAllIdentificationData(String id);


}
