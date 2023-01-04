package com.brightcoding.app.ws.meteo.app.ws.repositories;

import com.brightcoding.app.ws.meteo.app.ws.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface userRepositories extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);

//    @Query(value = "SELECT * FROM users", nativeQuery = true)
//    Page<UserEntity> findAllUser(Pageable pageableRequest);

    @Query("SELECT user FROM UserEntity user")
    Page<UserEntity> findAllUser(Pageable pageableRequest);

    // @Query(value = "SELECT * FROM users u where (u.first_name = ?1 OR u.last_name = ?1) AND u.email_verification_status=?2", nativeQuery = true)
   // Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, String search, int status);

//    @Query(value = "SELECT * FROM users u where (u.first_name = :search OR u.last_name = :search) AND u.email_verification_status=:status", nativeQuery = true)
//    Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, @Param("search") String search, @Param("status") int status);

    @Query(value = "SELECT * FROM users u where (u.first_name LIKE %:search% OR u.last_name LIKE %:search%) AND u.email_verification_status=:status", nativeQuery = true)
    Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, @Param("search") String search, @Param("status") int status);

}
