package com.slip.user.service;

import com.slip.user.Models.mongodb.ImageType;
import org.springframework.web.multipart.MultipartFile;


public abstract class ImageService {
    public abstract String uploadImage(MultipartFile image, ImageType imageType, String postRef);
}
