package com.example.talent3demoresepmakanan.repository;

import com.example.talent3demoresepmakanan.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Long> {

    List<Users> findByIsDeleted(Boolean isDeleted);

    @Query(value = "select * from users u where LOWER(l.username) LIKE %:name%",nativeQuery = true)
    List<Users> findByUsersName(String name);
}
