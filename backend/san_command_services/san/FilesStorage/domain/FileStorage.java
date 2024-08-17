package com.ponsun.san.FilesStorage.domain;


import com.ponsun.san.FilesStorage.request.CreateFileStorageRequest;
import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "kyc_document")

public class FileStorage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "kycId")
    private Integer kycId;

    @Column(name = "url")
    private String url;

    public static FileStorage create(final CreateFileStorageRequest createFileStorageRequest){
        final FileStorage fileStorage = new FileStorage();

       fileStorage.setKycId(createFileStorageRequest.getKycId());
       fileStorage.setUrl(createFileStorageRequest.getUrl());
       fileStorage.setStatus(Status.ACTIVE);
        return fileStorage;
    }

}
