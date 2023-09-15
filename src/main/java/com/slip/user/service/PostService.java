package com.slip.user.service;

import com.slip.user.Models.Post;

import java.util.List;

public interface PostService{
    Post savePost(Post post);

    Post getPost(Long id);

    List<Post> getAllPost();

    String  deletePost(Long id);
}
