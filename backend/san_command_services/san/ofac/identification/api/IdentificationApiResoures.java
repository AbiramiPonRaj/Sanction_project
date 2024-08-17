package com.ponsun.san.ofac.identification.api;

import com.ponsun.san.ofac.identification.data.IdentificationData;
import com.ponsun.san.ofac.identification.services.IdentificationWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/IdentificationApiResoures")
public class IdentificationApiResoures {
    private final IdentificationWriteService identificationWriteService;

    @GetMapping
    public List<IdentificationData> fetchAll(@RequestParam String id){
        return this.identificationWriteService.fetchAllIdentificationData(id);
    }}


