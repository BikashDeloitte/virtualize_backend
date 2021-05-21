package com.hu.Virtualize.repositories;

import com.hu.Virtualize.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {


    public Users findByusername(String username);
}
