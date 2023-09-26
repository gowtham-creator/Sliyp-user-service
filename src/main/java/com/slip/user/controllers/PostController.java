package com.slip.user.controllers;

import com.slip.user.Models.Post;
import com.slip.user.service.PostService;
import com.slip.user.util.JwtTokenUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createUserPost(@RequestBody Post post, @RequestHeader("Authorization") String authorizationHeader){
        final String userEmail= JwtTokenUtil.getEmailFromToken(authorizationHeader.substring(7));
        return postService.saveOrUpdatePost(post,userEmail);
    }
    @GetMapping("/{id}")
    public Post getUserPost(@PathVariable Long  id){
        return postService.getPost(id);
    }
    @GetMapping()
    public List<Post> getUserPostsByPagination(@RequestParam Long offset ,@RequestParam Long limit){
        return postService.getAllPost();
    }
    @DeleteMapping
    public String deleteUserPost(@RequestParam Long  id){
        return postService.deletePost(id);
    }

}
