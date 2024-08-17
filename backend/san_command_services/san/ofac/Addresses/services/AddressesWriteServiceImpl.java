package com.ponsun.san.ofac.Addresses.services;

import com.ponsun.san.ofac.Addresses.data.AddressesData;
import com.ponsun.san.ofac.Addresses.rowmapper.AddressesRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressesWriteServiceImpl implements AddressesWriteService {
    private final JdbcTemplate jdbcTemplate;
    private final AddressesRowMapper addressesRowMapper;


    @Override
    public List<AddressesData> fetchAllAddressesData(Integer id){
        final AddressesRowMapper rowMapper = new AddressesRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        //String whereClause = " WHERE a.`PrimaryKey`=b.`FK_Profile` AND b.`PrimaryKey`=c.`FK_Feature` AND c.`ID`=d.`FeatureVersionID` AND d.`FK_Location`=e.`PrimaryKey` AND f.`FK_Location`=e.`PrimaryKey`  AND g.`FK_LocationPart`=f.`PrimaryKey` AND LocPartTypeID=h.`ID` AND a.`id`=? AND i.`FK_Location`=e.`PrimaryKey`";
        String whereClause = " WHERE a.`PrimaryKey`=b.`FK_Profile` AND b.`PrimaryKey`=c.`FK_Feature` AND c.`ID`=d.`FeatureVersionID` AND d.`FK_Location`=e.`PrimaryKey` AND f.`FK_Location`=e.`PrimaryKey`  AND g.`FK_LocationPart`=f.`PrimaryKey` AND LocPartTypeID=h.`ID` AND a.`id`=? AND i.`FK_Location`=e.`PrimaryKey`) a GROUP BY IDs,countryName";
        Qry = Qry + whereClause;
        final List<AddressesData> addressesData = jdbcTemplate.query(Qry,new Object[]{id},addressesRowMapper);
        return addressesData;
    }
}
