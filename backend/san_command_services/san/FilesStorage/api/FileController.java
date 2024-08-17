package com.ponsun.san.FilesStorage.api;

import com.ponsun.san.FilesStorage.service.FileDownloadUtil;
import com.ponsun.san.FilesStorage.service.FileStorageWritePlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api/v1/FileUpload")
@Tag(name = "FileUploadApiResource")
public class FileController {
    @Autowired
    private FileStorageWritePlatformService fileStorageWritePlatformService;
    private FileDownloadUtil fileDownloadUtil;
    private final JdbcTemplate jdbcTemplate;

    @PostMapping("/files/upload")
    public ResponseEntity<String> uploadFiles(
            @RequestParam("files") MultipartFile[] files,
            Integer kycId
    ) {
        List<String> messages = new ArrayList<>();

        try {
            for (int j = 0; j < files.length; j++) {
                fileStorageWritePlatformService.save(files[j], kycId);
                messages.add(files[j].getOriginalFilename() + " [Successful]");
            }
            return ResponseEntity.ok(messages.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
}
