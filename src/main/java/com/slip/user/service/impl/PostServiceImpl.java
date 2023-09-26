package com.slip.user.service.impl;

import com.slip.user.Models.Post;
import com.slip.user.repositories.PostRepository;
import com.slip.user.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public Post saveOrUpdatePost(Post post,String userEmail){

        if(post.getPostRef()==null ) {
            post.setPostRef(UUID.randomUUID().toString());
        }
        Post createdPost = postRepository.save(post);
        postRepository.createRelation(userEmail,createdPost.getPostRef());
        return createdPost;
    }
    @Override
    public Post getPost(Long id){
        return postRepository.findById(id).orElseThrow();
    }
    @Override
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }
    @Override
    public String deletePost(Long id){
        postRepository.deleteById(id);
        return "User Post Deleted";
    }
}
