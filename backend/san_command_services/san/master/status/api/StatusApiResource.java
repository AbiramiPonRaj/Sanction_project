package com.ponsun.san.master.status.api;

import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.master.status.domain.Status;
import com.ponsun.san.master.status.request.CreateStatusRequest;
import com.ponsun.san.master.status.request.UpdateStatusRequest;
import com.ponsun.san.master.status.services.StatusReadPlatformServiceImpl;
import com.ponsun.san.master.status.services.StatusWritePlatformServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/Status")
@Tag(name = "StatusApiResource")
@CrossOrigin(origins = "http://localhost:3000")

public class StatusApiResource {
    private final StatusWritePlatformServiceImpl statusWritePlatformService;
    private final StatusReadPlatformServiceImpl statusReadPlatformService;

    @PostMapping("/createStatus")
    public Response saveStatus(@RequestBody CreateStatusRequest createStatusRequest) {
        Response response = this.statusWritePlatformService.createStatus(createStatusRequest);
        return response;
    }

    @GetMapping
    public List<Status> fetchAllStatus() {
        return this.statusReadPlatformService.fetchAllStatus();
    }

    @GetMapping("/{id}")
    public Status fetchStatusById(@PathVariable(name = "id") Integer id) {
        return this.statusReadPlatformService.fetchStatusById(id);
    }


    @PutMapping("/{id}")
    public Response updateStatus(@PathVariable Integer id, @RequestBody UpdateStatusRequest updateStatusRequest) {
        Response response = this.statusWritePlatformService.updateStatus(id, updateStatusRequest);
        return response;
    }


    @PutMapping("/{id}/block")
    public Response blockStatus(@PathVariable Integer id) {
        Response response = this.statusWritePlatformService.blockStatus(id);
        return response;
    }

    @PutMapping("/{id}/unblock")
    public Response unblockState(@PathVariable Integer id) {
        Response response = this.statusWritePlatformService.unblockStatus(id);
        return response;
    }
}
