package com.ponsun.san.sanction.nameDetails.services;


import com.ponsun.san.sanction.nameDetails.data.*;
import com.ponsun.san.sanction.nameDetails.rowmapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NameDetailsReadServiceImpl implements NameDetailsReadService {
    private final JdbcTemplate jdbcTemplate;
    private final WholeNameRowMapper wholeNameRowMapper;
    private final CityDetailsRowMapper cityDetailsRowMapper;

    @Override
    public List<WholeNameData> fetchAllWholeNameData(Integer Entity_logical_id) {
        final WholeNameRowMapper rowMapper = new WholeNameRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE a.Entity_logical_id= ? AND a.Naal_wholename !=''";
        Qry = Qry + whereClause;
        final List<WholeNameData> wholeNameData = jdbcTemplate.query(Qry, wholeNameRowMapper,
                new Object[]{Entity_logical_id}
        );
        return wholeNameData;
    }

    @Override
    public List<IdentificationDetailsData> fetchAllIdentificationData(Integer Entity_logical_id_Iden) {
        final IdentificationDetailsRowMapper rowMapper = new IdentificationDetailsRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE a.Entity_logical_id_Addr=?";
        Qry = Qry + whereClause;
        final List<IdentificationDetailsData> identificationDetailsData = jdbcTemplate.query(Qry, rowMapper,
                new Object[]{Entity_logical_id_Iden}
        );
        return identificationDetailsData;
    }


    @Override
    public List<AddressFileData> fetchAllAddressData(Integer Entity_logical_id_Addr) {
        final AddressFileRowMapper rowMapper = new AddressFileRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE a.Entity_logical_id_Addr=?";
        Qry = Qry + whereClause;
        final List<AddressFileData> addressFileDataList = jdbcTemplate.query(Qry, rowMapper,
                new Object[]{Entity_logical_id_Addr}
        );
        return addressFileDataList;
    }

    @Override
    public List<PersonalDetailsData> fetchAllDetailsData(Integer Entity_logical_id) {
        final PersonalDetailsRowMapper rowMapper = new PersonalDetailsRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE a.Entity_logical_id=? AND a.Naal_wholename !='' LIMIT 1";
        Qry = Qry + whereClause;
        final List<PersonalDetailsData> personalDetailsDataList = jdbcTemplate.query(Qry, rowMapper,
                new Object[]{Entity_logical_id}
        );
        return personalDetailsDataList;
    }

    @Override
    public List<BirthDetailsData> fetchAllBirthDetailsData(Integer Entity_logical_id_birth) {
        final BirthDetailsRowMapper rowMapper = new BirthDetailsRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE Entity_logical_id_birth= ? GROUP BY Entity_logical_id_birth";
        Qry = Qry + whereClause;
        final List<BirthDetailsData> birthDetailsDataList = jdbcTemplate.query(Qry, rowMapper,
                new Object[]{Entity_logical_id_birth}
        );
        return birthDetailsDataList;
    }
@Override
    public List<CityDetailsData> fetchAllCityDetailsData(Integer entityLogicalIdCiti) {
        String query = "SELECT " + cityDetailsRowMapper.tableSchema() +
                " WHERE a.Entity_logical_id_citi = ? " +
                " GROUP BY a.Entity_logical_id_citi";
        return jdbcTemplate.query(query, cityDetailsRowMapper, entityLogicalIdCiti);
    }
    @Override
    public List<AliasesDetailsData> fetchAllAliasesDetailsData(Integer Group_ID) {
        final AliasesDetailsRowMapper rowMapper = new AliasesDetailsRowMapper();
        String Qry = "SELECT " + rowMapper.tableSchema();
        String whereClause = " WHERE Group_ID=? GROUP BY 3 ORDER BY 2";
        Qry = Qry + whereClause;
        final List<AliasesDetailsData> aliasesDetailsData = jdbcTemplate.query(Qry, rowMapper,
                new Object[]{Group_ID}
        );
        return aliasesDetailsData;
    }

    @Override
    public List<NationalIdentificationData> fetchAllNationalIdentificationData(Integer Group_ID) {
        final NationalIdentificationNumberRowMapper rowMapper = new NationalIdentificationNumberRowMapper();
        String Qry = "SELECT * " + rowMapper.tableSchema();

        Qry = Qry ;
        final List<NationalIdentificationData> nationalIdentificationData = jdbcTemplate.query(Qry, rowMapper,
                new Object[]{Group_ID,Group_ID}
        );
        return nationalIdentificationData;
    }
}


