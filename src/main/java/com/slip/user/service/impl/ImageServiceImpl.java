package com.slip.user.service.impl;


import com.slip.user.Models.Post;
import com.slip.user.Models.User;
import com.slip.user.Models.mongodb.Image;
import com.slip.user.Models.mongodb.ImageType;
import com.slip.user.repositories.PostRepository;
import com.slip.user.repositories.mongodb.ImageRepository;
import com.slip.user.service.ImageService;
import com.slip.user.service.UserService;
import com.slip.user.util.AppUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl extends ImageService {
    private final ImageRepository imageRepository;
    private final UserService userService;
    private final PostRepository postRepository;

    public ImageServiceImpl(ImageRepository imageRepository, UserService userService, PostRepository postRepository) {
        this.imageRepository = imageRepository;
        this.userService = userService;
        this.postRepository = postRepository;
    }
    @Override
    public Image uploadImage(Image image){
        Image imageUploaded= imageRepository.save(image);
        if(ImageType.USER_PROFILE.equals(image.getImageType())){
            String email=AppUtils.getUserEmail();
            User user=userService.getUserByEmail(email);
            user.setProfileImgId(imageUploaded.getId());
            userService.saveUserInfo(user);
        }
        else if(ImageType.USER_POST.equals(image.getImageType())){
            Post post=postRepository.findById(Long.valueOf(image.getId())).orElseThrow();
            List<String> postImgIds=post.getPostImgIds();
            postImgIds.add(imageUploaded.getId());
            post.setPostImgIds(postImgIds);
            postRepository.save(post);
        }
        return imageUploaded;

    }

    @Override
    public Image downloadImage(String imageRef){
        return imageRepository.findById(imageRef).orElse(null);
    }
    @Override
    public List<Image> findAllObj(){
        return imageRepository.findAll();
    }
}
