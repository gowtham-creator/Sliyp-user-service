package com.slip.user.repositories;

import com.slip.user.Models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<User,Long>  {


 @Query("match (n:User) where n.handle =~ $handle return n")
 User getUserByHandle(@Param("handle") String handle);

 @Query("match (n:User) where n.email =~ $email return n")
 User getUserByEmail(@Param("email") String email);

 @Query("MATCH " +
         "(a:User), " +
         "(b:User) " +
         "WHERE a.handle =~ $loggedInUserHandle  AND b.handle =~ $targetUserHandle " +
         "CREATE (a)-[r:Follows]->(b) " +
         "RETURN type(r)" )
 User followUserWitHandle(@Param("loggedInUserHandle") String loggedInUserHandle,@Param("targetUserHandle") String targetUserHandle);

}
