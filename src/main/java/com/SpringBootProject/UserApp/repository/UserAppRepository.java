package com.SpringBootProject.UserApp.repository;
import com.SpringBootProject.UserApp.model.UserAppDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserAppRepository extends JpaRepository<UserAppDTO,Long>
{

    // custom native Query for fetching jwt_token from database user_app

    @Query(value = "select * from user_appdto where jwt_token = :jwtToken ", nativeQuery = true)
    List<UserAppDTO> userInfo(@Param("jwtToken") String jwtToken);



    @Query(value = "select * from user_appdto where userid = :userID",nativeQuery = true)
    UserAppDTO findByUserID(@Param("userID") String userID);


    @Query(value = "SELECT COUNT(user_appdto.jwt_token) FROM user_appdto WHERE user_appdto.jwt_token = :jwtToken",nativeQuery = true)
    int existJwtToken(@Param("jwtToken") String jwtToken);

}

