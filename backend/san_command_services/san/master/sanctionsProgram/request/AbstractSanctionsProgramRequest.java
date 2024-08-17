package com.ponsun.san.master.sanctionsProgram.request;

import lombok.Data;

@Data
public class AbstractSanctionsProgramRequest {
    private String PrimaryKey;
    private String ID;
    private String SubsidiaryBodyID;
    private String Text;
    private String FK_SanctionsProgramValues;
}
