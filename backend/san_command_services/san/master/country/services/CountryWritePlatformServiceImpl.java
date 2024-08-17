package com.ponsun.san.master.country.services;

import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.master.country.data.CountryDataValidator;
import com.ponsun.san.master.country.domain.Country;
import com.ponsun.san.master.country.domain.CountryRepository;
import com.ponsun.san.master.country.domain.CountryRepositoryWrapper;
import com.ponsun.san.master.country.request.CreateCountryRequest;
import com.ponsun.san.master.country.request.UpdateCountryRequest;
import com.ponsun.san.master.country.rowmapper.CountryRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryWritePlatformServiceImpl implements CountryWritePlatformService {
    private final CountryRepository countryRepository;
    private final CountryRepositoryWrapper countryRepositoryWrapper;
    private final CountryDataValidator countryDataValidator;
    private final CountryRowMapper countryRowMapper;
    private final JdbcTemplate jdbcTemplate;


    @Override
    @Transactional
    public Response createCountry(CreateCountryRequest createCountryRequest) {
        try{
            this.countryDataValidator.validateSaveCountryData(createCountryRequest);
            final Country country = Country.create(createCountryRequest);
            this.countryRepository.saveAndFlush(country);
            return Response.of(Long.valueOf(country.getPrimaryKey()));
        }catch (DataIntegrityViolationException e){
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response updateCountry(Integer id, UpdateCountryRequest updateCountryRequest) {
        try {
            this.countryDataValidator.validateUpdateCountryData(updateCountryRequest);
            final Country country = this.countryRepositoryWrapper.findOneWithNotFoundDetection(id);
            country.update(updateCountryRequest);
            this.countryRepository.saveAndFlush(country);
            return Response.of(Long.valueOf(country.getPrimaryKey()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}
