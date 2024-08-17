package com.ponsun.san.EhcachePOC.Data;

import lombok.Data;

@Data
public class OFACData {
    private Integer id;
    private String name;
    private String orgName;
    private Integer fileType;
    private String fileList;

    public OFACData(Integer id, String name,String orgName, Integer fileType, String fileList) {
        this.id = id;
        this.name = name;
        this.orgName=orgName;
        this.fileType = fileType;
        this.fileList = fileList;
    }
    public static OFACData newInstance(Integer id, String name,String orgName, Integer fileType, String fileList){
        return new OFACData(id,name,orgName,fileType,fileList);
    }
}

