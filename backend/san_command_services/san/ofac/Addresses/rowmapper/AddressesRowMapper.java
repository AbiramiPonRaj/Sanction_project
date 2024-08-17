package com.ponsun.san.ofac.Addresses.rowmapper;
import com.ponsun.san.ofac.Addresses.data.AddressesData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
public class AddressesRowMapper implements RowMapper<AddressesData> {
    private final String schema;

    public AddressesRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM `profile` a,`feature` b,`featureversion` c,`featureversionreference` d,`location` e,`locationpart` f,`locationpartvalue` g,`locparttype` h,`locationcountry` i");
        this.schema = builder.toString();
    }
    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" MAX(CASE WHEN TYPE = 'REGION' THEN VALUE END) AS REGION," +
                "    MAX(CASE WHEN TYPE = 'ADDRESS1' THEN VALUE END) AS Address1," +
                "    MAX(CASE WHEN TYPE = 'ADDRESS2' THEN VALUE END) AS Address2," +
                "    MAX(CASE WHEN TYPE = 'ADDRESS3' THEN VALUE END) AS ADDRESS3," +
                "    MAX(CASE WHEN TYPE = 'CITY' THEN VALUE END) AS City," +
                "    MAX(CASE WHEN TYPE = 'STATE/PROVINCE' THEN VALUE END) AS PROVINCE," +
                "    MAX(CASE WHEN TYPE = 'POSTAL CODE' THEN VALUE END) AS POSTAL," +
                "    countryName" +
                " FROM (" +
                " SELECT e.`PrimaryKey` AS IDs,VALUE,TEXT AS TYPE," +
                " (SELECT TEXT FROM `country` WHERE id=i.CountryID) AS countryName");


        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public AddressesData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String region = rs.getString("region");
        final String address1 = rs.getString("address1");
        final String address2 = rs.getString("address2");
        final String address3 = rs.getString("address3");
        final String city = rs.getString("city");
        final String province = rs.getString("province");
        final String postal = rs.getString("postal");
        final String countryName = rs.getString("countryName");
        return new AddressesData(region,address1,address2,address3,city,province,postal,countryName);

    }
}