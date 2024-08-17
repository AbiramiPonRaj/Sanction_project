package com.ponsun.san.ofac.Count.data;

import lombok.Data;

@Data
public class CountData {
    private Integer id;
    private String name;
    private String orgName;
    private Integer fileType;
    private String fileList;

    public CountData(Integer id, String name,String orgName,Integer fileType,String fileList) {
        this.id = id;
        this.name = name;
        this.orgName=orgName;
        this.fileType=fileType;
        this.fileList=fileList;
    }
    public static CountData newInstance(Integer id, String name,String orgName,Integer fileType,String fileList){
        return new CountData(id,name,orgName,fileType,fileList);
    }
}
