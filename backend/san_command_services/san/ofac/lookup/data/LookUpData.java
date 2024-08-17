package com.ponsun.san.ofac.lookup.data;
import lombok.Data;

@Data
public class LookUpData {
    private Integer id;
    private String type;//
    private String name;//
    private Integer minimum_name_score;//
    private String address;
    private String city;
    private String state;
    private String country;//
    private String list;

    public LookUpData(Integer id, String type, String name, Integer minimum_name_score, String address, String city, String state, String country, String list) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.minimum_name_score = minimum_name_score;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.list = list;
    }

    public static LookUpData newInstance(Integer id, String type, String name, Integer minimum_name_score, String address, String city, String state, String country, String list){
        return new LookUpData(id,type,name,minimum_name_score,address,city,state,country,list);
    }
}
