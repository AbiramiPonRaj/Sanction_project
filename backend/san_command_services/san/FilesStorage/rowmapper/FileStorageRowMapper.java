package com.ponsun.san.FilesStorage.rowmapper;


import com.ponsun.san.FilesStorage.data.FileStorageData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@Service
@Slf4j

public class FileStorageRowMapper implements RowMapper<FileStorageData> {
    
    private final String schema;

    public FileStorageRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM kyc_document kd");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("kd.id as id");
        builder.append("kd.documentType as documentType");
        builder.append("kd.kycId as kycId");
        builder.append("kd.updated_at as dt");
        builder.append("kd.url as url");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public FileStorageData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id = rs.getInt("id");
        final Integer pathId = rs.getInt("kycId");
        final String documentType=rs.getString("documentType");
        final String dt = rs.getString("dt");
        final String url =rs.getString("url");
        return new FileStorageData(id,pathId,documentType,dt,url);
    }
}
