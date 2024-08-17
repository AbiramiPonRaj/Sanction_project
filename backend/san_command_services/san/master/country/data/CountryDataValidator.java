package com.ponsun.san.master.country.data;




import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.master.country.request.CreateCountryRequest;
import com.ponsun.san.master.country.request.UpdateCountryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CountryDataValidator {
    public void validateSaveCountryData(final CreateCountryRequest request){
        if(request.getText() == null || request.getText().equals("")){
            throw new EQAS_SAN_ApplicationException("Text parameter required");
        }
    }
    public void validateUpdateCountryData(final UpdateCountryRequest request){
        if(request.getText() == null || request.getText().equals("")){
            throw new EQAS_SAN_ApplicationException("Text parameter required");
        }
    }
}
