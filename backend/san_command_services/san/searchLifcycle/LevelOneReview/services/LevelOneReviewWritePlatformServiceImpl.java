package com.ponsun.san.searchLifcycle.LevelOneReview.services;

import com.ponsun.san.searchLifcycle.LevelOneReview.data.LevelOneReviewData;
import com.ponsun.san.searchLifcycle.LevelOneReview.rowmapper.LevelOneReviewRowmapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LevelOneReviewWritePlatformServiceImpl implements LevelOneReviewWritePlatformService {
    private final JdbcTemplate jdbcTemplate;
    private final LevelOneReviewRowmapper levelOneReviewRowmapper;

    @Override
    public List<LevelOneReviewData> fetchAllLevelOneReviewData(){
        final LevelOneReviewRowmapper rowmapper = new LevelOneReviewRowmapper();
        String Qry = "SELECT " + rowmapper.tableSchema();
        String whereClause= " WHERE b.id = d.hit_id AND b.id = c.hitdata_id AND c.level_id = 1 AND c.valid = 1";
        Qry = Qry+whereClause;
        final List<LevelOneReviewData> levelOneReviewData = jdbcTemplate.query(Qry, levelOneReviewRowmapper);
        return levelOneReviewData;
    }

}
