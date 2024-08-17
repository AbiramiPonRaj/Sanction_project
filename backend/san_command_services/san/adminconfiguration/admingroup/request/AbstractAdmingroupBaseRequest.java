package com.ponsun.san.adminconfiguration.admingroup.request;

import lombok.Data;

  @Data
public abstract class AbstractAdmingroupBaseRequest {
    private Integer id;
    private String moduleUrl;
    private String name;
    private Boolean valid;
    private Integer uid;
    private Integer euid;
}
