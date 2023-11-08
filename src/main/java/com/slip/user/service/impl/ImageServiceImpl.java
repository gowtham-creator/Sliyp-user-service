package com.slip.user.service.impl;


import com.slip.user.Models.Post;
import com.slip.user.Models.User;
import com.slip.user.Models.mongodb.ImageType;
import com.slip.user.repositories.PostRepository;
import com.slip.user.service.GoogleCloudStorageService;
import com.slip.user.service.ImageService;
import com.slip.user.service.UserService;
import com.slip.user.util.AppUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.slip.user.constants.GeneralConstants.EMPTY_STRING;

@Service
public class ImageServiceImpl extends ImageService {
    private final UserService userService;
    private final PostRepository postRepository;
    public final GoogleCloudStorageService googleCloudStorageService;


    public ImageServiceImpl( UserService userService, PostRepository postRepository, GoogleCloudStorageService googleCloudStorageService) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.googleCloudStorageService = googleCloudStorageService;
    }
    @Override
    public String uploadImage(MultipartFile image, ImageType imageType, String postRef) {
        try {
            if (ImageType.USER_PROFILE.equals(imageType)) {

                User user = userService.getUserByEmail(AppUtils.getUserEmail());
                user.setProfileImgUrl(
                        googleCloudStorageService.uploadImage(image.getBytes(),
                                String.join(EMPTY_STRING, user.getRef().toString(), imageType.name()),
                                image.getContentType())
                );
                userService.saveUserInfo(user);

            } else if (ImageType.USER_POST.equals(imageType)) {
                Post post = postRepository.findById(Long.valueOf(postRef)).orElseThrow();
                List<String> postImgIds = post.getPostImgUrls();
                postImgIds.add(
                        googleCloudStorageService.uploadImage(image.getBytes(),
                                String.join(EMPTY_STRING, post.getPostRef(), imageType.name()),
                                image.getContentType()));
                post.setPostImgUrls(postImgIds);
                postRepository.save(post);
            }
            return "image uploaded successfully";
        }catch (Exception ex){ throw new RuntimeException("Please upload a different file");}

    }
}
