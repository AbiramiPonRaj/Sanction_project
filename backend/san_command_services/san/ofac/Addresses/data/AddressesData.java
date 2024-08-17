package com.ponsun.san.ofac.Addresses.data;
import lombok.Data;

@Data
public class AddressesData {
    private String region;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String province;
    private String postal;
    private String countryName;

    public AddressesData(String region, String address1, String address2, String address3, String city, String province, String postal, String countryName) {
        this.region = region;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.province = province;
        this.postal = postal;
        this.countryName = countryName;
    }
    public static AddressesData newInsatnce(String region, String address1, String address2, String address3, String city, String province, String postal, String countryName){
        return new AddressesData(region,address1,address2,address3,city,province,postal,countryName);
    }
}
