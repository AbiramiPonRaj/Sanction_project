package com.ponsun.san.FilesStorage.request;

import lombok.Data;

@Data
public class AbstractFileStorageRequest {

    private Integer id;
    private String DocumentType;
    private Integer kycId;
    private String dt;
    private String url;
}
