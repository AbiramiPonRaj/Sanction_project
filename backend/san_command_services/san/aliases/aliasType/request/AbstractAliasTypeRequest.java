package com.ponsun.san.aliases.aliasType.request;

import lombok.Data;

@Data
public class AbstractAliasTypeRequest {
    private String PrimaryKey;
    private String ID;
    private String Text;
    private String FK_AliasTypeValues;
}
