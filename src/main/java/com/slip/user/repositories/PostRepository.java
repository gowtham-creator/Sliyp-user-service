package com.slip.user.repositories;

import com.slip.user.Models.Post;
import com.slip.user.Models.User;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends Neo4jRepository<Post,Long> {
//    @Query("")
//    void createHasPostedRelation(String userId , Long postId);
//
//    @Query("")
//    Boolean hasPostedRelationExist(String userId , Long postId);

}
