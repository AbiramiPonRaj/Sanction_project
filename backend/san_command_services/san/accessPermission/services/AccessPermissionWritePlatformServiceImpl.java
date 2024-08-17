package com.ponsun.san.accessPermission.services;

import com.ponsun.san.accessPermission.data.AccessPermissionData;
import com.ponsun.san.accessPermission.rowmapper.AccessPermissionRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Slf4j  
@RequiredArgsConstructor
public class AccessPermissionWritePlatformServiceImpl implements  AccessPermissionWritePlatformService {
    private final JdbcTemplate jdbcTemplate;
    private final AccessPermissionRowMapper accessPermissionRowMapper;
    @Override
    public List<AccessPermissionData> fetchAllAccessPermissionData(String uid){
        Map<String,Object> parameters = new HashMap<>();
        final AccessPermissionRowMapper rowMapper = new AccessPermissionRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause =" WHERE a.id=b.mod_id AND c.mod_id=a.id AND c.mod_det_id=b.id AND c.uid=? AND a.id ORDER BY a.orderno;";
        Qry = Qry + whereClause;
        final List<AccessPermissionData> accessPermissionData = jdbcTemplate.query(Qry,new Object[]{uid},accessPermissionRowMapper);
        return accessPermissionData;
    }
}
