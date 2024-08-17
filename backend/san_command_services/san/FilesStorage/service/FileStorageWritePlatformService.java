package com.ponsun.san.FilesStorage.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageWritePlatformService {
//    Response createFileStorage(CreateFileStorageRequest createFileStorageRequest);
    void save(MultipartFile file, int kycId);
//    void companysave(MultipartFile companyfiles, int companyfilesPathId, Integer AssociatedId);
}
