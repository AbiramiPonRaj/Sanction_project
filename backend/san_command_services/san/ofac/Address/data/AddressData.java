


package com.ponsun.san.ofac.Address.data;
import lombok.Data;

@Data

public class AddressData {
    private String Address;


    public AddressData(String Address) {
        this.Address = Address;
    }

    public static AddressData newInstance(String Address) {
        return new AddressData(Address);
    }
}
