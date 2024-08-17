package com.ponsun.san.FilesStorage.domain;


import com.ponsun.san.FilesStorage.request.AbstractFileStorageRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
public class FileStorageRepositoryWrapper extends AbstractFileStorageRequest {
    private final FileStorageRepository fileStorageRepository;

    public FileStorageRepositoryWrapper(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
    }

    @Transactional
    public FileStorage findOneWithNotFoundDetection(final Integer id) {
        return this.fileStorageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("FileStorage Not found " + id));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
