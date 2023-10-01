package com.slip.user.service;

import com.slip.user.Models.mongodb.Image;

import java.util.List;

public abstract class ImageService {
    public abstract Image uploadImage(Image image);

    public abstract Image downloadImage(String imageRef);

    public abstract List<Image> findAllObj();

    public abstract String deleteImage(String imageRef);
}
