package com.slip.user.service;

import com.slip.user.Models.Post;
import com.slip.user.dto.Post.PostAction;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PostService{
    Post saveOrUpdatePost(Post post,String Email);

    Post getPost(Long id);

    Page<Post> getAllPost(int offset , int limit);

    String  deletePost(Long id);

    String createPostAction(PostAction postAction, String userEmail);
}
