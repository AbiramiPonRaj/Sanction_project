package com.ponsun.san.FilesStorage.service;


import com.ponsun.san.FilesStorage.domain.FileStorageRepository;
import com.ponsun.san.FilesStorage.request.CreateFileStorageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageWritePlatformServiceImpl implements FileStorageWritePlatformService {
    private final FileStorageRepository fileStorageRepository;
    private final JdbcTemplate jdbcTemplate;

    private final String baseRoot = "D:\\uploadImages";



    @Override
    public void save(MultipartFile file, int kycId) {
        String resolvedRootDirectory = "";


            resolvedRootDirectory = "Kyc\\profile";


        Path root = Paths.get(baseRoot, resolvedRootDirectory);
//        updateFamilyDocument(imgId);
//        System.out.println("imgId:" + imgId);
        try {
            Files.createDirectories(root);

            String fileExtensions = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

            CreateFileStorageRequest createFamilyDocumentsRequest = new CreateFileStorageRequest();
            createFamilyDocumentsRequest.setDocumentType(fileExtensions);
            createFamilyDocumentsRequest.setKycId(kycId);

            Integer imageId = 1;
            System.out.println("imageId: " + imageId);

            String filename = imageId + "." + fileExtensions;
            Files.copy(file.getInputStream(), root.resolve(filename));
            System.out.println("filename: " + filename);

        } catch (IOException e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("Filename already exists.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateFamilyDocument(Integer imgId) {
        try {
            String sql = "update pep_document_family set status = 'D', updated_at = NOW() WHERE id = ? AND status = 'A'";
            jdbcTemplate.update(sql, imgId);
        } catch (DataAccessException e) {
            System.err.println("Error updating the document: " + e.getMessage());
            e.printStackTrace();
        }
    }
}