package com.alkemy.ong.objectstorage;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {
    String upload(MultipartFile multipartFile);
    void delete(String fileName);
}
