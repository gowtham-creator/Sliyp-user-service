package com.slip.user.repositories;

import com.slip.user.Models.Post;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends Neo4jRepository<Post,Long> {

    public final String CREATE_USER_POST_RELATIONSHIP = "CREATE (user:User {email:~$email})-[hasPosted:HAS_POSTED]->(post:Post {id: ~$postId}) RETURN type(hasPosted)";
    public final  String CHECK_USER_POST_RELATIONSHIP = "MATCH (user:User {email: ~$email})-[hasPosted:HAS_POSTED]->(post:Post {id: ~$postId}) return hasPosted is not null";

    @Query(CREATE_USER_POST_RELATIONSHIP)
    void createRelation(@Param("email") String email ,@Param("postId") Long  postId);

    @Query(CHECK_USER_POST_RELATIONSHIP)
    Boolean isRelationExist(@Param("email") String email  ,@Param("postId") Long  postId);
}
