package com.alkemy.ong.objectstorage.s3;

import com.alkemy.ong.objectstorage.ImageStorage;
import com.alkemy.ong.objectstorage.InternalException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StorageAmazonDefault implements ImageStorage {
    @Value("${application.bucket.name}")
    private String bucketName;

    private AmazonS3 s3Client;

    private StorageAmazonDefault(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }


    private String uploadFile(MultipartFile multipartFile) {
        final String fileName = multipartFile.getOriginalFilename();
        final File file = toFile(multipartFile);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
        file.delete();
        return s3Client.getUrl(bucketName,fileName).toString();
    }

    private void deleteFile(String fileName) {
        s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }

    private File toFile(MultipartFile file) {
        File converted = new File(file.getOriginalFilename());
        try (FileOutputStream outputStream = new FileOutputStream((converted))) {
            outputStream.write(file.getBytes());
        } catch (IOException e) {
            throw new InternalException("Error converting multipartFile to file");
        }
        return converted;
    }

    @Override
    public String upload(MultipartFile multipartFile) {
        return uploadFile(multipartFile);
    }

    @Override
    public void delete(String fileName) {
        deleteFile(fileName);
    }
}
