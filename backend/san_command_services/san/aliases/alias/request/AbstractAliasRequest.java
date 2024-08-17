package com.ponsun.san.aliases.alias.request;

import lombok.Data;

@Data
public class AbstractAliasRequest {

    private String PrimaryKey;
    private String FixedRef;
    private String AliasTypeID;
    private String Primary;
    private String LowQuality;
    private String FK_Identity;

}
