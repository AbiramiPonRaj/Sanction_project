package com.ponsun.san.master.list.api;



import com.ponsun.san.infrastructure.utils.Response;

import com.ponsun.san.master.list.domain.Lists;
import com.ponsun.san.master.list.request.CreateListRequest;
import com.ponsun.san.master.list.request.UpdateListRequest;
import com.ponsun.san.master.list.services.ListReadPlatformService;
import com.ponsun.san.master.list.services.ListWritePlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/List")
@Tag(name = "ListApiResources")
public class ListApiResources {
    private final ListWritePlatformService listWritePlatformService;
    private final ListReadPlatformService listReadPlatformService;
    @PostMapping("/CreateListRequest")
    public Response saveList(@RequestBody CreateListRequest createListRequest){
        Response response = this.listWritePlatformService.createList(createListRequest);
        return response;
    }
    @GetMapping
    public List<Lists> fetchAll(){ return this.listReadPlatformService.fetchAllList();}

    @GetMapping("/{id}")
    public Lists fetchListById(@PathVariable(name = "id")Integer id){
        return this.listReadPlatformService.fetchListById(id);
    }

    @PutMapping("/{id}")
    public Response updateList(@PathVariable Integer id, @RequestBody UpdateListRequest updateListRequest){
        Response response = this.listWritePlatformService.updateList(id,updateListRequest);
        return  response;
    }
}
