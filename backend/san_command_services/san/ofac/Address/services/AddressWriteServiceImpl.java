



package com.ponsun.san.ofac.Address.services;

import com.ponsun.san.ofac.Address.data.AddressData;
import com.ponsun.san.ofac.Address.rowmapper.AddressRowMapper;
import com.ponsun.san.ofac.Program.rowmapper.ProgramRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressWriteServiceImpl implements AddressWriteService {
    private final JdbcTemplate jdbcTemplate;
    private final AddressRowMapper addressRowMapper;

    @Override
    public List<AddressData> fetchAllAddressData(String id) {
        final ProgramRowMapper rowMapper = new ProgramRowMapper();
        String sql = "SELECT Address(?) as Address";

        final List<AddressData> addressData = jdbcTemplate.query(sql, new Object[]{id}, addressRowMapper);

        return addressData;
    }

}


