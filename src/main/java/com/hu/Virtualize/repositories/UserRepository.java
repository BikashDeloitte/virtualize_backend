package com.hu.Virtualize.repositories;

import com.hu.Virtualize.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserEmail(String userEmail);

    UserEntity findByUserName(String userName);
}
