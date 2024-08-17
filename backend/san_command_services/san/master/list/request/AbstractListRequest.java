package com.ponsun.san.master.list.request;

import lombok.Data;

@Data
public class AbstractListRequest {

    private String PrimaryKey;
    private String ID;
    private String Text;
    private String FK_ListValues;
}
