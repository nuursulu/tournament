package com.example.mycli.repository;

import com.example.mycli.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);
    UserEntity findById(long clientID);

    @Modifying
    @Query("update UserEntity u set u.roleEntity = :role where u.login = :login")
    void updateUserRole(String login, String role);
}
