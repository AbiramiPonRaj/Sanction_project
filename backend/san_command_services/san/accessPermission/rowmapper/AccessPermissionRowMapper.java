package com.ponsun.san.accessPermission.rowmapper;

import com.ponsun.san.accessPermission.data.AccessPermissionData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class AccessPermissionRowMapper implements RowMapper<AccessPermissionData> {
    public final String schema;

    public AccessPermissionRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM `admin_config_module` a,`admin_config_module_det` b,`admin_user_rights` c");
        this.schema = builder.toString();
    }
    public String schema(){ return this.schema;}
    public String tableSchema(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.id AS header, b.name AS NAME,b.name AS CODE,CONCAT('/',b.name) AS link");
        builder.append(this.schema);
        return builder.toString();
    }
    @Override
    public AccessPermissionData mapRow(ResultSet rs,int rowNum) throws SQLException{
        final String name = rs.getString("name");
        final String code = rs.getString("code");
        final String link = rs.getString("link");
        final String uid = "";
        final String header = rs.getString("header");
        return AccessPermissionData.newInstance(name,code,link,uid,header);
    }
}
