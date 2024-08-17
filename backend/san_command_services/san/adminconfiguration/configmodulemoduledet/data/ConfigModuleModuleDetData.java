package com.ponsun.san.adminconfiguration.configmodulemoduledet.data;

import lombok.Data;

@Data
public class ConfigModuleModuleDetData {
private Integer modid;
private Integer moddetid;
private String modname;
private String moddetname;


public ConfigModuleModuleDetData(final Integer modid, final Integer moddetid, final String modname, final String moddetname){
    this.modid = modid;
    this.moddetid = moddetid;
    this.modname = modname;
    this.moddetname = moddetname;
}
public static ConfigModuleModuleDetData newInstance(Integer modid, Integer moddetid, String modname, String moddetname) {
return new ConfigModuleModuleDetData(modid,moddetid,modname,moddetname);
}
}
