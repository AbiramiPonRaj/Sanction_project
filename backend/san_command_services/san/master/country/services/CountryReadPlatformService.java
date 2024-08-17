package com.ponsun.san.master.country.services;

import com.ponsun.san.master.country.domain.Country;

import java.util.List;

public interface CountryReadPlatformService {
    List<Country> fetchAllCountry();

    Country fetchCountryById(Integer id);
}
