package com.slip.user.repositories;

import com.slip.user.Models.Post;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends Neo4jRepository<Post,Long> {


    @Query("Match (user:User {email: $email}) " +
            "MATCH (post:Post {postRef: $postRef}) " +
            "CREATE (user)-[hasPosted:HAS_POSTED{createdAt:date()}]->(post) return type(hasPosted) ")
    String createRelation(@Param("email") String email ,@Param("postRef") String  postRef);

    @Query("MATCH (user:User {email:  $email})-[hasPosted:HAS_POSTED]->(post:Post {postRef:  $postRef}) return hasPosted is not null")
    Boolean isRelationExist(@Param("email") String email  ,@Param("postRef") String  postRef);
}
