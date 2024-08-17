package com.ponsun.san.EhcachePOC.Service;

import com.ponsun.san.EhcachePOC.Data.OFACData;
import com.ponsun.san.ofac.Count.data.CountData;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;

public interface OFACSearchService {

    List<OFACData> fetchAllOFACData();
    String deleteOfacData();
}
