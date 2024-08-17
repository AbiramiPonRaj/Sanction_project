package com.ponsun.san.ofac.OfacDto.service;
import com.ponsun.san.ofac.OfacDto.Data.OfacDto;

import java.util.List;

public interface OfacDtoWriteService {
    List<OfacDto> fetchAllOfacDto();
}