package com.ponsun.san.sanction.nameDetails.rowmapper;

import com.ponsun.san.sanction.nameDetails.data.AddressDetailsData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Service
@Slf4j
public class AddressDetailsRowMapper implements RowMapper<AddressDetailsData> {
    private final String schema;

    public AddressDetailsRowMapper() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM uk_san a");
        this.schema = builder.toString();
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.Address_1 as Address_1, ");
        builder.append("a.Address_2 as Address_2, ");
        builder.append("a.Address_3 as Address_3, ");
        builder.append("a.Address_4 as Address_4, ");
        builder.append("a.Address_5 as Address_5, ");
        builder.append("a.Address_6 as Address_6, ");
        builder.append("a.Post_ZipCode as Post_ZipCode, ");
        builder.append("a.Country as Country, ");
        builder.append("a.Other_Information as Other_Information ");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public AddressDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String address1 = rs.getString("Address_1");
        final String address2 = rs.getString("Address_2");
        final String address3 = rs.getString("Address_3");
        final String address4 = rs.getString("Address_4");
        final String address5 = rs.getString("Address_5");
        final String address6 = rs.getString("Address_6");
        final String postZipCode = rs.getString("Post_ZipCode");
        final String country = rs.getString("Country");
        final String otherInformation = rs.getString("Other_Information");

        return new AddressDetailsData(address1, address2, address3, address4,
                address5, address6, postZipCode, country, otherInformation);
    }
}
