package com.ponsun.san.searchLifcycle.LevelTwoReview.services;


import com.ponsun.san.searchLifcycle.LevelTwoReview.data.LevelTwoReviewData;
import com.ponsun.san.searchLifcycle.LevelTwoReview.rowmapper.LevelTwoReviewRowmapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LevelTwoReviewWritePlatformServiceImpl implements LevelTwoReviewWritePlatformService {
    private final JdbcTemplate jdbcTemplate;
    private final LevelTwoReviewRowmapper levelTwoReviewRowmapper;

    @Override
    public List<LevelTwoReviewData> fetchAllLevelTwoReviewData(){
        final LevelTwoReviewRowmapper rowmapper = new LevelTwoReviewRowmapper();
        String Qry ="SELECT " + rowmapper.tableSchema();
        String whereClause=" WHERE b.id = d.hit_id AND b.id = c.hitdata_id AND c.statusid = 2 AND c.level_id =2 AND c.valid= 1";
        Qry = Qry + whereClause;
        final List<LevelTwoReviewData> levelTwoReviewData = jdbcTemplate.query(Qry,levelTwoReviewRowmapper);
        return levelTwoReviewData;
    }

}
