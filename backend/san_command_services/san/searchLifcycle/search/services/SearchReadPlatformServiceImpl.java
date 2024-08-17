package com.ponsun.san.searchLifcycle.search.services;

import com.ponsun.san.Record.RecordDtos;
import com.ponsun.san.searchLifcycle.hitrecord.data.HitRecordData;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecordRepository;
import com.ponsun.san.searchLifcycle.hitrecord.rowmapper.HitRecordRowMapper;
import com.ponsun.san.searchLifcycle.hitrecord.services.HitRecordReadPlatformService;
import com.ponsun.san.searchLifcycle.search.data.SearchData;
import com.ponsun.san.searchLifcycle.search.data.SearchDto;
import com.ponsun.san.searchLifcycle.search.domain.Search;
import com.ponsun.san.searchLifcycle.search.domain.SearchRepository;
import com.ponsun.san.searchLifcycle.search.domain.SearchRepositoryWrapper;
import com.ponsun.san.searchLifcycle.search.rowmapper.SearchRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchReadPlatformServiceImpl implements SearchReadPlatformService {

    private final SearchRepositoryWrapper searchRepositoryWrapper;
    private final JdbcTemplate jdbcTemplate;
    private final SearchRepository searchRepository;
    private final SearchRowMapper searchRowMapper;
    private final HitRecordRowMapper hitRecordRowMapper;
    private final HitRecordRepository hitRecordRepository;
    private final HitRecordReadPlatformService hitRecordReadPlatformService;
    private final SearchWritePlatformService searchWritePlatformService;

    @Override
    public Search fetchSearchById(Integer id) {
        return this.searchRepository.findById(id).get();
    }
    @Override
    public List<Search> fetchAllSearch() {
        return this.searchRepository.findAll();
    }

    @Override
    public List<SearchData>fetchAllSearchDetailData(String fromDate, String toDate){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fromDate", fromDate);
        parameters.put("toDate", toDate);
        final SearchRowMapper rowMapper = new SearchRowMapper();
        String Qry = "SELECT "  + rowMapper.tableSchema();
        String whereClause = " WHERE DATE(cs.created_at) BETWEEN ? AND ? ";
        Qry = Qry + whereClause;
        final List<SearchData> searchDetailData = jdbcTemplate.query(Qry,searchRowMapper,
                new Object[] {fromDate , toDate});
        return searchDetailData;
    }

    @Override
    public List<HitRecordData>fetchAllRecordData(String fromDate, String toDate){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("frmDate", fromDate);
        parameters.put("toDate", toDate);
        final HitRecordRowMapper rowMapper=new HitRecordRowMapper();
        String Qry = "SELECT "  + rowMapper.tableSchema();
        String whereClause = " WHERE DATE(hit.created_at) BETWEEN ? AND ? ";
        Qry = Qry + whereClause;
        final List<HitRecordData> hitRecordDataList = jdbcTemplate.query(Qry,hitRecordRowMapper,
                new Object[] {fromDate , toDate}
        );
        return hitRecordDataList;
    }

    @Override
    public List<RecordDtos> fetchAllDetailData(String fromDate, String toDate){
        SearchDto searchDtoList = new SearchDto();
        List<RecordDtos> recordDtosList = new ArrayList<>();
        List<SearchData> searchDataList = fetchAllSearchDetailData(fromDate, toDate);
        ModelMapper modelMapper = new ModelMapper();

        for (SearchData searchData: searchDataList)
        {
            SearchDto search = new SearchDto();
            search.setId(searchData.getId());
            search.setName(searchData.getName());
            search.setMatchingScore(searchData.getMatching_score());
            search.setListId(searchData.getListId());
            search.setTypeId(searchData.getTypeId());
            search.setStateId(searchData.getStateId());
            search.setCountryId(searchData.getCountryId());
            search.setIdentity(searchData.getIdentity());
            search.setLevelId(searchData.getLevelId());
            search.setUid(searchData.getUid());
            search.setValid(searchData.getValid());

//            SearchDto request = modelMapper.map(searchData, SearchDto.class);
            List<HitRecordData> hitRecord= this.hitRecordReadPlatformService.fetchAllHitDataById(searchData.getId());
            search.setHitRecordData(hitRecord);
            recordDtosList.add(RecordDtos.newInstance(search));
        }
        return recordDtosList;
    }
}
