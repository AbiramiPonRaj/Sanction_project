package com.ponsun.san.FilesStorage.data;

import lombok.Data;

@Data
public class FileStorageData {

    private Integer id;
    private Integer kycId;
    private String documentType;
    private String dt;
    private String url;

    public FileStorageData(final Integer id, final Integer kycId,final String documentType, final String dt, final String url ) {
        this.id=id;
        this.kycId=kycId;
        this.documentType=documentType;
        this.dt=dt;
        this.url=url;
    }
    public static FileStorageData newInstance(final Integer id, final Integer kycId,final String documentType, final String dt, final String url ) {
        return new FileStorageData(id,kycId,documentType,dt,url);
    }
}

