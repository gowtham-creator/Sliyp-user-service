package com.slip.user.repositories;

import com.slip.user.Models.Post;
import com.slip.user.Models.User;

import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface PostRepository extends Neo4jRepository<Post,Long> {

}
