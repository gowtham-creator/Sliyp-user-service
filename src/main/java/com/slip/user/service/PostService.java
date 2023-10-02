package com.slip.user.service;

import com.slip.user.Models.Post;
import com.slip.user.dto.Post.PostAction;

import java.util.List;

public interface PostService{
    Post saveOrUpdatePost(Post post,String Email);

    Post getPost(Long id);

    List<Post> getAllPost();

    String  deletePost(Long id);

    String createPostAction(PostAction postAction, String userEmail);
}
