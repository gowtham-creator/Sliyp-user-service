package com.slip.user.service;

import com.slip.user.Models.Post;

import java.util.List;

public interface PostService{
    Post saveOrUpdatePost(Post post,String Email);

    Post getPost(Long id);

    List<Post> getAllPost();

    String  deletePost(Long id);
}
