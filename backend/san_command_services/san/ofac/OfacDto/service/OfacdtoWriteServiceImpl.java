package com.ponsun.san.ofac.OfacDto.service;
import com.ponsun.san.ofac.OfacDto.Data.OfacDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfacdtoWriteServiceImpl implements OfacDtoWriteService {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<OfacDto> fetchAllOfacDto() {

        return this.fetchAllOfacDto();

    }
}



