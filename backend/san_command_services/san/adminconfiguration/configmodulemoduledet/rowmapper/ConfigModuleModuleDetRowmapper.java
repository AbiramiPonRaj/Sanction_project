package com.ponsun.san.adminconfiguration.configmodulemoduledet.rowmapper;

import com.ponsun.san.adminconfiguration.configmodulemoduledet.data.ConfigModuleModuleDetData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class ConfigModuleModuleDetRowmapper implements RowMapper<ConfigModuleModuleDetData> {

    public final String schema;
public ConfigModuleModuleDetRowmapper(){

//    SELECT a.id,b.id,a.name,b.name FROM admin_config_module a,admin_config_module_det b WHERE a.id=b.mod_id AND a.valid=1 AND b.valid=1  ORDER BY a.orderno;


    final StringBuilder builder = new StringBuilder(200);
    builder.append(" FROM admin_config_module a,admin_config_module_det b");
    this.schema = builder.toString();
}

public String schema()
{return this.schema;
}

    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" a.id as modid,b.id as moddetid,a.name as modname,b.name as moddetname");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public ConfigModuleModuleDetData mapRow(ResultSet rs, int rowNum) throws SQLException{
    final Integer modid = rs.getInt("modid");
    final Integer moddetid = rs.getInt("moddetid");
    final String modname = rs.getString("modname");
    final String moddetname = rs.getString("moddetname");
    return ConfigModuleModuleDetData.newInstance(modid,moddetid,modname,moddetname);
    }
}

