package com.ponsun.san.adminconfiguration.configmodulemoduledet.services;

import com.ponsun.san.adminconfiguration.configmodulemoduledet.data.ConfigModuleModuleDetData;
import com.ponsun.san.adminconfiguration.configmodulemoduledet.rowmapper.ConfigModuleModuleDetRowmapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConfigModuleModuleDetWritePlatformServiceImpl implements ConfigModuleModuleDetWritePlatformService {
    private final JdbcTemplate jdbcTemplate;
    private final ConfigModuleModuleDetRowmapper configModuleModuleDetRowmapper;

    @Override
    public List<ConfigModuleModuleDetData> fetchAllListofAlertData() {

        final ConfigModuleModuleDetRowmapper rowmapper = new ConfigModuleModuleDetRowmapper();

        String Qry = "SELECT "  + rowmapper.tableSchema();
        String whereClause = " WHERE a.id = b.mod_id AND a.valid=1 AND b.valid=1 ORDER BY a.orderno";
        Qry = Qry + whereClause;

        final List<ConfigModuleModuleDetData> ConfigModuleModuleDetData = jdbcTemplate.query(Qry, configModuleModuleDetRowmapper
        );
        return ConfigModuleModuleDetData;

    }
}
