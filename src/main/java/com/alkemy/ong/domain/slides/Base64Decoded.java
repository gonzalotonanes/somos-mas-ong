package com.alkemy.ong.domain.slides;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;

public class Base64Decoded implements MultipartFile {
     private byte[] bytesDecoded;
     private String name;
     private String extra;


    public Base64Decoded(byte[] bytesDecoded, String name) {
        this.bytesDecoded = bytesDecoded;
        this.name = name;
        this.extra = this.getClass().toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return name ;
    }

    @Override
    public String getContentType() {
        return extra;
    }


    @Override
    public boolean isEmpty() {
        return bytesDecoded == null || bytesDecoded.length == 0;
    }

    @Override
    public long getSize() {
        return bytesDecoded.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return bytesDecoded;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(bytesDecoded);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        try (FileOutputStream outputStream = new FileOutputStream(dest)) {
            outputStream.write(bytesDecoded);
        }
    }
}
