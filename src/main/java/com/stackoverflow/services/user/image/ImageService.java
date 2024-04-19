package com.stackoverflow.services.user.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void storeFile(MultipartFile multipartFile, Long answerId) throws IOException;
}
