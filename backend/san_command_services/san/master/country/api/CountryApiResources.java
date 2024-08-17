package com.ponsun.san.master.country.api;



import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.master.country.domain.Country;
import com.ponsun.san.master.country.request.CreateCountryRequest;
import com.ponsun.san.master.country.request.UpdateCountryRequest;
import com.ponsun.san.master.country.services.CountryReadPlatformService;
import com.ponsun.san.master.country.services.CountryWritePlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/Country")
@Tag(name = "CountryApiResources")
public class CountryApiResources {
    private final CountryWritePlatformService countryWritePlatformService;
    private final CountryReadPlatformService countryReadPlatformService;
    @PostMapping("/CreateCountryRequest")
    public Response saveCountry(@RequestBody CreateCountryRequest createCountryRequest){
        Response response = this.countryWritePlatformService.createCountry(createCountryRequest);
        return response;
    }
    @GetMapping
    public List<Country> fetchAll(){ return this.countryReadPlatformService.fetchAllCountry();}

    @GetMapping("/{id}")
    public Country fetchCountryById(@PathVariable(name = "id")Integer id){
        return this.countryReadPlatformService.fetchCountryById(id);
    }

    @PutMapping("/{id}")
    public Response updateCountry(@PathVariable Integer id, @RequestBody UpdateCountryRequest updateCountryRequest){
        Response response = this.countryWritePlatformService.updateCountry(id,updateCountryRequest);
        return  response;
    }
}
