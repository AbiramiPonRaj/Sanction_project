package com.ponsun.san.sanction.unDetails.services;

import com.ponsun.san.sanction.unDetails.data.*;

import java.util.List;

public interface UnDetailsReadService {
    List<UnAliasDetailsData> fetchAliasDetails(Integer DATAID);


    //List<UnAddressData> fetchAllAddressData(Integer entityLogicalIdAddr);


    List<UnDesignationDetailsData> fetchDesignationDetails(int DATAID);

    UnPersonalDetailsData fetchAllDetailsData(Integer DATAID);
}
