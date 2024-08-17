package com.ponsun.san.FilesStorage.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@SpringBootApplication

public class FileDownloadUtil {

    private Path foundFile;
    private final String baseRoot = "D:\\uploadImages";


    public Resource getFileAsResource(String imgId, Integer pathId) throws IOException {

        String resolvedRootDirectory = "";


        if (pathId == 1) {
            resolvedRootDirectory = "family\\profile";
        } else if (pathId == 2) {
            resolvedRootDirectory = "family\\party";
        } else if (pathId == 3) {
            resolvedRootDirectory = "companyDIN";
        } else if (pathId == 4) {
            resolvedRootDirectory = "companiesLLP";
        }

        Path root = Paths.get(baseRoot, resolvedRootDirectory);
        try {
        // Filter files based on the file code
        Optional<Path> file = Files.list(root)
                .filter(f -> FilenameUtils.removeExtension(f.getFileName().toString()).equals(imgId))
                .findFirst();

        if (file.isPresent()) {
            foundFile = file.get();
            return new UrlResource(foundFile.toUri());
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Resource getCompanyFileAsResource(Integer companyId,String imageName,Integer pathId) throws IOException {
        String resolvedRootDirectory = "";

        if (pathId == 5) {
            resolvedRootDirectory = "about_companyDetails\\"+companyId;
        } else if (pathId == 6) {
            resolvedRootDirectory = "DirectorsList\\" + companyId;
        }


        Path root = Paths.get(baseRoot, resolvedRootDirectory);

        Optional<Path> file = Files.list(root)
                .filter(f -> FilenameUtils.removeExtension(f.getFileName().toString()).equals(imageName))
                .findFirst();

        if (file.isPresent()) {
            foundFile = file.get();
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }
}
//    public Resource getcompanyFile(String companyId, Integer pathId) throws IOException {
//
//        String resolvedRootDirectory = "";
//
//        if (pathId == 5) {
//            resolvedRootDirectory = "about_companyDetails";
//        } else if (pathId == 6) {
//            resolvedRootDirectory = "DirectorsList";
//        }
//
//        String query = "SELECT CONCAT(id,'.',documentType) FROM pep_document_companies WHERE companyId="+companyId+" AND STATUS='A'";
//
//        System.out.println("query: "+query);
//        String filename = (query);
//
//        if (filename != null) {
//            Path imagePath = Paths.get(baseRoot, resolvedRootDirectory, filename);
//
//            System.out.println("imagePath: "+imagePath);
//
//        }
//        return null;
//    }}
//            File imageFile = imagePath.toFile();
//            ImageIO.read(imageFile);

//    Optional<Path> file = Files.list(root)
//            .filter(f -> FilenameUtils.removeExtension(f.getFileName().toString()).equals(companyId))
//            .findFirst();
//
//        if (file.isPresent()) {
//                foundFile = file.get();
//                return new UrlResource(foundFile.toUri());
//                }