package com.ponsun.san.ofac.OfacDto.api;

import com.ponsun.san.ofac.OfacDto.Data.OfacDto;
import com.ponsun.san.ofac.OfacDto.service.OfacDtoWriteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/OfacDto")
@Tag(name = "OfacDtoApiResource")
public class OfacDtoApiResources {
    private final OfacDtoWriteService ofacDtoWriteService;

    @GetMapping
    public List<OfacDto> fetchAll(){return this.ofacDtoWriteService.fetchAllOfacDto();}

}
