package com.slip.user.repositories;

import com.slip.user.Models.Tasks;
import com.slip.user.Models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepostitory extends Neo4jRepository<Tasks,Long> {


    @Query("match (n:Tasks) where n.userRef =~ $userRef return n")
    List<Tasks> getUserByUserId(@Param("userRef") String userRef);
    @Query("match (n:Tasks) where n.userRef =~ $userRef return count(n)")
    Integer getUserTaskCount(@Param("userRef") String userRef);
}
