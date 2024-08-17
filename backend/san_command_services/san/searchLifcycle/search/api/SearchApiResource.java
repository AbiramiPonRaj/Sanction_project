package com.ponsun.san.searchLifcycle.search.api;

import com.ponsun.san.Record.RecordDtos;
import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.search.domain.Search;
import com.ponsun.san.searchLifcycle.search.request.CreateSearchRequest;
import com.ponsun.san.searchLifcycle.search.request.UpdateSearchRequest;
import com.ponsun.san.searchLifcycle.search.services.SearchReadPlatformService;
import com.ponsun.san.searchLifcycle.search.services.SearchWritePlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "SearchApiResource")
public class SearchApiResource {

    private final SearchWritePlatformService searchWritePlatformService;
    private final SearchReadPlatformService searchReadPlatformService;

    @PostMapping("/createSearch")
    public Response saveSearch(@RequestBody CreateSearchRequest createSearchRequest) {
        Response response = this.searchWritePlatformService.createSearch(createSearchRequest);
        return response;
    }

    @GetMapping
    public List<Search> fetchAll() {
        return this.searchReadPlatformService.fetchAllSearch();
    }

    @GetMapping("/{id}")
    public Search fetchSearchById(@PathVariable (name = "id") Integer id) {
        return this.searchReadPlatformService.fetchSearchById(id);
    }
    @PutMapping("/{id}")
    public Response updateSearch(@PathVariable Integer id, @RequestBody UpdateSearchRequest updateSearchRequest) {
        Response response = this.searchWritePlatformService.updateSearch(id, updateSearchRequest);
        return response;
    }

    @PutMapping("/{id}/block")
    public Response blockSearch(@PathVariable Integer id) {
        Response response = this.searchWritePlatformService.blockSearch(id);
        return response;
    }

    @PutMapping("/{id}/unblock")
    public Response unblockSearch(@PathVariable Integer id) {
        Response response = this.searchWritePlatformService.unblockSearch(id);
        return response;
    }
    @GetMapping("/Search")
    public List<RecordDtos> fetchAllDetailData(@RequestParam String fromDate , @RequestParam String toDate){
        return this.searchReadPlatformService.fetchAllDetailData(fromDate , toDate);}

    }
