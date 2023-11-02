package com.example.resumegenerator.repositories;

import com.example.resumegenerator.entities.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByUserEmail(String email);
    List<AppUser> findAll();

    AppUser getReferenceById(Integer id);

    boolean existsByUserEmail(String userEmail);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO app_user(user_id,user_password,user_email,user_password_salt) " +
            "values (:id,:password,:email,:salt ) ;")
    Integer saveWithId(@Param("id")Integer id,@Param("password") String password,@Param("email") String email,@Param("salt") String salt);
}