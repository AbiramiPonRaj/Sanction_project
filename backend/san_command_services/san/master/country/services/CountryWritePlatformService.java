package com.ponsun.san.master.country.services;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.master.country.data.CountryData;
import com.ponsun.san.master.country.request.CreateCountryRequest;
import com.ponsun.san.master.country.request.UpdateCountryRequest;

import java.util.List;

public interface CountryWritePlatformService {
    Response createCountry(CreateCountryRequest createCountryRequest);
    Response updateCountry(Integer id, UpdateCountryRequest updateCountryRequest);
}
